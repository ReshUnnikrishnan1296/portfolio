import requests
from tabulate import tabulate
import ast

def sendRequest(data, location):
	server_add = ""
	if(location == '1'):
		server_add = 'http://104.154.210.21/'
	elif(location == '2'):
		server_add = 'http://35.225.125.209/'
	else:
		server_add = 'http://localhost:5000/'

	query_type = data['type']

	if(query_type == 'select'):
		params = {'tableName': data['table'], 'fields': data['fields'], 'conditions': data['conditions'], 'is_transaction': data['is_transaction']}
		resp = requests.post(server_add+'select', json=params)
		if resp.status_code != 200:
			print("Error in select Operation.")
			return 0
		else:
			resp = resp.json()
			print(tabulate(resp['result'], headers='firstrow', tablefmt='fancy_grid'))
			return 1

	elif(query_type == 'insert'):
		params = {'tableName': data['table'], 'data': data['values'], 'is_transaction': data['is_transaction']}
		resp = requests.post(server_add+'insert', json=params)
		if(resp.status_code == 200):
			print("Insert Operation Successful.")
			return 1
		else:
			print(resp.status_code)
			print("Error in Insert Operation.")
			if(params['is_transaction'] == 1):
				sendRequest({'type':'rollback'}, 1)
				return 0

	elif(query_type == 'update'):
		params = {'tableName': data['table'], 'values': data['values'], 'conditions': data['conditions'], 'is_transaction': data['is_transaction']}
		resp = requests.post(server_add+'update', json=params)
		if(resp.status_code == 200):
			print("Update Operation Successful.")
			return 1
		else:
			print("Error in Update Operation.")
			if(params['is_transaction'] == 1):
				sendRequest({'type':'rollback'}, 1)
				return 0


	elif(query_type == 'delete'):
		params = {'tableName': data['table'], 'condition': data['conditions'], 'is_transaction': data['is_transaction']}
		resp = requests.post(server_add+'delete', json=params)
		if(resp.status_code == 200):
			print("Delete Operation Successful.")
			return 1
		else:
			print("Error in Delete Operation.")
			if(params['is_transaction'] == 1):
				sendRequest({'type':'rollback'}, 1)
				return 0

	elif(query_type == 'createTable'):
		print(data)
		params = {'tableName': data['table'], 'metaData': data['columns'], 'keys': data['keys']}
		resp = requests.post(server_add+'createTable', json=params)

		if(resp.status_code == 200):
			data['server'] = location

			f_gdd = open("gdd.txt","a")
			f_gdd.write(data['table']+"="+location+"\n")
			f_gdd.close()

			f_tables_metada = open("tables_metadata.txt","a")
			f_tables_metada.write(str(data)+"\n")
			f_tables_metada.close()
			return 1
		else:
			print("Error in Creating Table.")

	elif(query_type == 'deleteTable'):
		params = {'tableName': data['table']}
		resp = requests.post(server_add+'deleteTable', json=params)
		if(resp.status_code == 200):
			params['server'] = server_add
			with open("tables_metadata.txt", "r") as f_table_read:
				rows = f_table_read.readlines()
			f_table_read.close()
			with open("tables_metadata.txt", "w") as f_table_write:
				for line in rows:
					line2 = line.split('\n')[0]
					line2 = ast.literal_eval(line)
					if(line2['table'] != params['tableName']):
						f_table_write.write(line)
			f_table_write.close()
			return 1
		else:
			print("Error in Deleting Table.")

	elif(query_type == 'erd'):
		resp = requests.get(server_add+'erd')
		resp = resp.json()
		for key in resp:
			print("Table: "+key)
			print(tabulate(resp[key], headers='firstrow', tablefmt='fancy_grid'))
		else:
			print("Error in Generating ERD.")

	elif(query_type == 'commit'):
		resp1 = requests.get('http://104.154.210.21/commit')
		resp2 = requests.get('http://35.225.125.209/commit')
		resp3 = requests.get('http://localhost:5000/commit')
		if(resp1.status_code == 200 and resp2.status_code == 200 and resp3.status_code == 200):
			print("Commit Successful.")
		else:
			print("Error in Commit Operation.")

	elif(query_type == 'rollback'):
		print("Rolling back")
		resp1 = requests.get('http://104.154.210.21/rollback')
		resp2 = requests.get('http://35.225.125.209/rollback')
		resp3 = requests.get('http://localhost:5000/rollback')
		if(resp1.status_code == 200 and resp2.status_code == 200 and resp3.status_code == 200):
			print("Rollback Successful.")