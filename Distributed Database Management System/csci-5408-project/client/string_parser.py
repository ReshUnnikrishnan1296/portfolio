#methods to take input SQL query and parse

import logging
import time

formatter = logging.Formatter('%(asctime)s %(levelname)s %(message)s')

def setup_logger(name, log_file, level=logging.INFO):

    handler = logging.FileHandler(log_file, mode='a')        
    handler.setFormatter(formatter)

    logger = logging.getLogger(name)
    logger.setLevel(level)
    logger.addHandler(handler)

    return logger

general_logger = setup_logger('general_logger', 'General.log')

event_logger = setup_logger('event_logger', 'Events.log')

# u_query = input("Please enter the SQL query : ")
    
def processUserQuery(query):
    event_logger.info("User query : "+query)
    x = query.split();
    for i in x:
        if(i.lower() == "create"):
            return processCreateQuery(x,query)
        elif(i.lower() == "insert"):
            return processInsertQuery(x)
        elif(i.lower() == "update"):
            return processUpdateQuery(x)
        elif(i.lower() == "delete"):
            return processDeleteQuery(x)
        elif(i.lower() == "select"):  
            return processSelectQuery(x)


def processInsertQuery(queryParts):
    
    query_dict = {"type":"insert"}
    table = ""
    values = []
    for i in range(len(queryParts)):
        if(queryParts[i].lower() == "insert"):
            table = queryParts[i+2]
        if(queryParts[i].lower() == "values"):
            v = queryParts[i+1].replace("(","")
            v = v.replace(")","")
            values = v.split(",")
        i=i+1
    query_dict["table"]=table
    query_dict["values"]=values
    print(query_dict)
    
    return query_dict
    
def processUpdateQuery(queryParts):
    query_dict = {"type":"update"}
    table = ""
    for i in range(len(queryParts)):
        if(queryParts[i].lower() == "update"):
            table = queryParts[i+1]
        elif(queryParts[i].lower() == "set"):
            upd_val = queryParts[i+1]
        elif(queryParts[i].lower() == "where"):
            conditions = queryParts[i+1]
        i = i+1
    query_dict["table"]=table
    query_dict["values"]=upd_val
    query_dict["conditions"]=conditions
    print(query_dict)
    return query_dict

def processDeleteQuery(queryParts):
    query_dict = {"type":"delete"}
    table = ""
    conditions=[]
    for i in range(len(queryParts)):
        if(queryParts[i].lower() == "delete"):
            table = queryParts[i+2]
        elif(queryParts[i].lower() == "where"):
            conditions = queryParts[i+1]
        i = i+1
    query_dict["table"]=table
    query_dict["conditions"]=conditions
    # print(query_dict)
    return query_dict

def processSelectQuery(queryParts):
    query_dict = {"type":"select"}
    table = ""
    fields = []
    conditions=[]
    for i in range(len(queryParts)):
        if(queryParts[i].lower() == "select"):
            if(queryParts[i+1] == '*'):
                fields = ["all"]
            else:
                fields = queryParts[i+1].lower().split(',')
        elif(queryParts[i].lower() == "from"):
            table = queryParts[i+1]
        elif(queryParts[i].lower() == "where"):
            conditions = queryParts[i+1]
        i = i+1
    query_dict["table"]=table
    query_dict["fields"]=fields
    query_dict["conditions"]=conditions
    
    # print(query_dict)
    return query_dict

def processCreateQuery(queryParts,query):
    query_dict = {"type":"create"}
    table = ""
    col_values = ""
    for i in range(len(queryParts)):
        if(queryParts[i].lower() == "create"):
            table = queryParts[i+2]
        i = i+1
    val = query.split("(")
    for i in range(len(val)):
        if(i==1):
            col_values = val[i].replace(")","")
           
    query_dict["table"]=table
    query_dict["column_values"]=col_values
    # print(query_dict)
    return query_dict

# processUserQuery(u_query)
