import json
from tabulate import tabulate

def select():
    request = '{"tableName":"users","fields":"all","conditions":"height=3"}'
    reqData = json.loads(request)
    tableName = reqData["tableName"]
    fields = reqData["fields"]
    conditions = reqData["conditions"]
    headers = []
    table_display=[]
    with open("database/metadata/"+tableName+"_metadata.txt","r") as f_metaData:
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
        if line == "":
            break
        line_list.pop()
        if(conditions == ""):
            table_display.append(line_list)
        else:
            if(line_list[pos_index] == cond_val):
                table_display.append(line_list)
        
    data_file.close()
    print(tabulate(table_display, headers='firstrow', tablefmt='fancy_grid'))

#select()

def update():
    request = '{"tableName":"Employee","values":"emp_name=john","conditions":"emp_id=1"}'
    reqData = json.loads(request)
    tableName = reqData["tableName"]
    values = reqData["values"]
    conditions = reqData["conditions"]
    headers = []
    with open("database/metadata/"+tableName+"_metadata.txt","r") as f_metaData:
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

#update()
