from flask import Flask
from flask import request
from tabulate import tabulate
import os
from os import path
import logging
import time
app = Flask(__name__)

formatter = logging.Formatter('%(asctime)s %(levelname)s %(message)s')

def setup_logger(name, log_file, level=logging.INFO):

    handler = logging.FileHandler(log_file, mode='a')        
    handler.setFormatter(formatter)

    logger = logging.getLogger(name)
    logger.setLevel(level)
    logger.addHandler(handler)

    return logger

general_logger = setup_logger('general_logger', 'client\General.log')

event_logger = setup_logger('event_logger', 'client\Events.log')

@app.route('/createTable', methods = ['POST'])
def createTable():
	reqData = request.get_json()
	print(reqData)
	tableName = reqData["tableName"]
	metadata = reqData["metaData"]
	keydata = reqData["keys"]
	f_metaData = open("database/metadata/"+tableName+".txt","w")
	metadatLines=[]
	for key, value in metadata.items():
		f_metaData.write(key+"="+value+"\n")
	f_metaData.close()

	f_keys = open("database/metadata/keys/"+tableName+".txt","w")
	f_keys.write("pk="+str(keydata["pk"])+"\n")
	f_keys.write("fk="+str(keydata["fk"])+"\n")
	f_keys.write("fk_table="+keydata["fk_table"]+"\n")
	f_keys.write("fk_column="+keydata["fk_column"]+"\n")
	f_keys.close()
	f_table = open("database/"+tableName+".txt","w")
	f_table.close()
	event_logger.info("Table name : "+tableName+" created successfully")
	database_state()
	return "Success"

@app.route('/deleteTable', methods = ['POST'])
def deleteTable():
	reqData = request.get_json()
	tableName = reqData["tableName"]
	os.remove("database/metadata/"+tableName+".txt")
	os.remove("database/metadata/keys/"+tableName+".txt")
	os.remove("database/"+tableName+".txt")
	event_logger.info("Table name : "+tableName+" deleted successfully")
	database_state()
	return "Success"

@app.route('/getAllTables', methods = ['GET'])
def getAllTables():
	arr = os.listdir('database')
	if ".DS_Store" in arr: arr.remove(".DS_Store")
	if "metadata" in arr: arr.remove("metadata")
	for i,fname in enumerate(arr):
		arr[i] = fname.rsplit('.',1)[0]
	filenames = {"tables": arr}
	return filenames

@app.route('/insert', methods = ['POST'])
def insert():
	reqData = request.get_json()
	tableName = reqData["tableName"]
	data = reqData["data"]
	is_transaction = reqData['is_transaction']

	f_keys = open("database/metadata/keys/"+tableName+".txt","r")
	pk = ((f_keys.readline()).split("=")[1]).split("\n")[0]
	fk = ((f_keys.readline()).split("=")[1]).split("\n")[0]
	fk_table = ((f_keys.readline()).split("=")[1]).split("\n")[0]
	fk_column = ((f_keys.readline()).split("=")[1]).split("\n")[0]

	if(pk != " "):
		pk = int(pk)
		data_file=open("database/"+tableName+".txt", "r")
		while data_file:
			print('hi')
			line  = data_file.readline()
			line_list = line.split(";")
			line_list[-1] = line_list[-1].split("\n")[0]
			if line == "":
				break
			ind = pk-1
			if(line_list[ind] == data[ind]):
				return "PK Error", 400

	if(fk != " "):
		fk = int(fk)
		fk_table_metaData = []
		with open("database/metadata/"+fk_table+".txt","r") as fk_table_metaData:
			for line in fk_table_metaData:
				colName = line.rsplit('=',1)[0]
				fk_table_metaData.append(colName)

		dataIndex=0

		for i,elem in enumerate(fk_table_metaData):
			if elem==fk_column:
				dataIndex=i

		fk_flag = False

		data_file=open("database/"+fk_table+".txt", "r")
		while data_file:
			line  = data_file.readline()
			line_list = line.split(";")
			line_list[-1] = line_list[-1].split("\n")[0]
			if line == "":
				break
			ind = fk-1
			if(line_list[ind] == data[ind]):
				fk_flag = True

			if(not fk_flag):
				return "FK Error", 401


	metaData = []
	with open("database/metadata/"+tableName+".txt","r") as f_metaData:
		for line in f_metaData:
			dataType = line.rsplit('=',1)[1]
			dataType = dataType.rsplit('\n',1)[0]
			metaData.append(dataType)

	for i,value in enumerate(data):
		if(metaData[i] == "varchar"):
			data[i] = str(data[i])
		if(metaData[i] == "int"):
			data[i] = int(data[i])
		if(metaData[i] == "float"):
			data[i] = float(data[i])

	if((is_transaction == 1) and (not path.exists("database/"+tableName+".txt_backup"))):
		with open("database/"+tableName+".txt", "r") as f_table_read:
			rows = f_table_read.readlines()
		f_table_read.close()
		with open("database/"+tableName+".txt_backup", "w") as f_table_write:
			for line in rows:
				f_table_write.write(line)		
		f_table_write.close()

	data=';'.join([str(elem) for elem in data])
	f_table = open("database/"+tableName+".txt","a")
	f_table.write(data+"\n")
	f_table.close()
	event_logger.info("1 row inserted into "+tableName+" successfully")
	database_state()
	return "Success"

@app.route('/delete', methods = ['POST'])
def delete():
	reqData = request.get_json()
	tableName = reqData["tableName"]
	condition = reqData["condition"]
	is_transaction = reqData['is_transaction']

	metaData = []
	with open("database/metadata/"+tableName+".txt","r") as f_metaData:
		for line in f_metaData:
			colName = line.rsplit('=',1)[0]
			metaData.append(colName)

	condition_col = condition.rsplit('=',1)[0]
	condition_val = condition.rsplit('=',1)[1]

	dataIndex=0

	for i,elem in enumerate(metaData):
		if elem==condition_col:
			dataIndex=i

	with open("database/"+tableName+".txt", "r") as f_table_read:
		rows = f_table_read.readlines()
	f_table_read.close()

	if((is_transaction == 1) and (not path.exists("database/"+tableName+".txt_backup"))):
		with open("database/"+tableName+".txt_backup", "w") as f_table_write:
			for line in rows:
				f_table_write.write(line)		
		f_table_write.close()

	with open("database/"+tableName+".txt", "w") as f_table_write2:
		for line in rows:
			row = line.split(';')
			if(row[dataIndex].rsplit('\n',1)[0]!=str(condition_val)):
				f_table_write2.write(line)
		f_table_write2.close()
	database_state()
	event_logger.info("1 row deleted from "+tableName+" successfully")
	return "Success"

@app.route('/select', methods = ['POST'])
def select():
    # request = '{"tableName":"users","fields":"all","conditions":"height=3"}'
    reqData = request.get_json()
    tableName = reqData["tableName"]
    fields = reqData["fields"]
    conditions = reqData["conditions"]
    headers = []
    table_display=[]
    with open("database/metadata/"+tableName+".txt","r") as f_metaData:
	    for line in f_metaData:
		    colName = line.rsplit('=',1)[0]
		    headers.append(colName)
    table_display.append(headers)
    if(conditions!=""):
        cond_col_name=conditions.split("=")[0]
        cond_val=conditions.split("=")[1]
        pos_index=headers.index(cond_col_name)
    data_file=open("database/"+tableName+".txt", "r")
    while data_file:
        line  = data_file.readline()
        line_list = line.split(";")
        line_list[-1] = line_list[-1].split("\n")[0]
        if line == "":
            break
        if(conditions == ""):
            table_display.append(line_list)
        else:
        	print(line_list[pos_index])
        	if(line_list[pos_index] == cond_val):
        		table_display.append(line_list)
        
    data_file.close()
    print(tabulate(table_display, headers='firstrow', tablefmt='fancy_grid'))

    return {'result': table_display}

@app.route('/update', methods = ['POST'])
def update():
    # request = '{"tableName":"users","values":"name=ram","conditions":"name=rahul"}'
    reqData = request.get_json()
    print(reqData)
    tableName = reqData["tableName"]
    values = reqData["values"]
    conditions = reqData["conditions"]
    is_transaction = reqData['is_transaction']

    headers = []
    with open("database/metadata/"+tableName+".txt","r") as f_metaData:
        for line in f_metaData:
            colName = line.rsplit('=',1)[0]
            headers.append(colName)
    if(conditions!=""):
        cond_col_name=conditions.split("=")[0]
        cond_val=conditions.split("=")[1]
        pos_index=headers.index(cond_col_name)

    if(values!=""):
        val_col_name=values.split("=")[0]
        val=values.split("=")[1]
        val_pos_index=headers.index(val_col_name)

    with open("database/"+tableName+".txt", "r") as f_table_read:
        rows = f_table_read.readlines()
    f_table_read.close()

    if((is_transaction == 1) and (not path.exists("database/"+tableName+".txt_backup"))):
    	with open("database/"+tableName+".txt_backup", "w") as f_table_write:
    		for line in rows:
    			f_table_write.write(line)
    		f_table_write.close()

    with open("database/"+tableName+".txt", "w") as f_table_write:
        for line in rows:
            row = line.split(';')
            if(row[pos_index].rsplit('\n',1)[0]!=cond_val):
                f_table_write.write(line)
            else:
                upd_line=""
                #row[-1]=row[-1].replace("\n","")
                for n, i in enumerate(row):
                    if n == int(val_pos_index):
                        row[n] = val
                print(row)
                for n, i in enumerate(row):
                    print(i)
                    if(n==len(row)-1):
                        upd_line=upd_line+row[n]
                    else:
                        upd_line=upd_line+row[n]+";"
                f_table_write.write(upd_line) 
    
    f_table_write.close()
    event_logger.info("1 row updated in "+tableName+" successfully")
    database_state()
    return "Success"

@app.route('/erd', methods = ['GET'])
def erd():
	metadata_files = os.listdir('database/metadata')
	if ".DS_Store" in metadata_files: metadata_files.remove(".DS_Store")
	if "keys" in metadata_files: metadata_files.remove("keys")

	header=["Column Name","Column Type","Primary Key", "Foreign Key", "Reference Table", "Reference Column"]

	erd_data = {};
	for i,fname in enumerate(metadata_files):
		table_name = fname.rsplit('.',1)[0]
		erd_data[table_name] = [header]

		f_metaData = open("database/metadata/"+fname,"r")
		while f_metaData:
			line  = f_metaData.readline()
			line_list = line.split("=")
			line_list[-1] = line_list[-1].split("\n")[0]
			if line == "":
			    break
			erd_data[table_name].append([line_list[0], line_list[1], "", "", "", ""])

		f_keys = open("database/metadata/keys/"+fname,"r")

		pk = ((f_keys.readline()).split("=")[1]).split("\n")[0]
		if(pk != " "):
			pk=int(pk)
			erd_data[table_name][pk][2] = "Yes"

		fk = ((f_keys.readline()).split("=")[1]).split("\n")[0]
		if(fk != " "):
			fk=int(fk)
			erd_data[table_name][fk][3] = "Yes"

			fk_table = ((f_keys.readline()).split("=")[1]).split("\n")[0]
			erd_data[table_name][fk][4] = fk_table

			fk_column = ((f_keys.readline()).split("=")[1]).split("\n")[0]
			erd_data[table_name][fk][5] = fk_column

	return erd_data

@app.route('/commit', methods = ['GET'])
def commit():
	bckp_files = os.listdir('database')
	if ".DS_Store" in bckp_files: bckp_files.remove(".DS_Store")
	if "metadata" in bckp_files: bckp_files.remove("metadata")
	for i,fname in enumerate(bckp_files):
		fname_split_list = fname.rsplit('_',1)
		if((len(fname_split_list) != 1) and (fname_split_list[-1] == "backup")):
			os.remove("database/"+fname)
	return "Success"

@app.route('/rollback', methods = ['GET'])
def rollback():
	bckp_files = os.listdir('database')
	if ".DS_Store" in bckp_files: bckp_files.remove(".DS_Store")
	if "metadata" in bckp_files: bckp_files.remove("metadata")
	for i,fname in enumerate(bckp_files):
		fname_split_list = fname.rsplit('_',1)
		if((len(fname_split_list) != 1) and (fname_split_list[-1] == "backup")):
			os.remove("database/"+fname_split_list[0])

			with open("database/"+fname, "r") as f_table_read:
				rows = f_table_read.readlines()
			f_table_read.close()
			with open("database/"+fname_split_list[0], "w") as f_table_write:
				for line in rows:
					f_table_write.write(line)		
			f_table_write.close()

			os.remove("database/"+fname)		
	return "Success"

def database_state():
    filenames = getAllTables()
    print(filenames)
    for key in filenames.keys():
        if(key == "tables"):
            for tableName in filenames[key]:
                print(tableName)
                data_file=open("database/"+tableName+".txt", "r")
                table_display = []
                while data_file:
                    line  = data_file.readline()
                    line_list = line.split(";")
                    line_list[-1] = line_list[-1].split("\n")[0]
                    if line == "":
                        break
                    table_display.append(line_list)
                print(len(table_display))
                general_logger.info("State of the database : Table name -> "+tableName+" number of rows : "+str(len(table_display)))
                

database_state()

if __name__ == '__main__':
   app.run(debug=True)
