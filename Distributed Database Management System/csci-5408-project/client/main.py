import getpass
global username
from string_parser import *
from send_request import *
import logging
import time
from tabulate import tabulate

formatter = logging.Formatter('%(asctime)s %(levelname)s %(message)s')

def encrypt(text,s):
    result = ""
    for i in range(len(text)):
        char = text[i]
        if (char.isupper()):
            result += chr((ord(char) + s-65) % 26 + 65)
        else:
            result += chr((ord(char) + s - 97) % 26 + 97)
    return result

def setup_logger(name, log_file, level=logging.INFO):

    handler = logging.FileHandler(log_file, mode='a')        
    handler.setFormatter(formatter)

    logger = logging.getLogger(name)
    logger.setLevel(level)
    logger.addHandler(handler)

    return logger

general_logger = setup_logger('general_logger', 'General.log')

event_logger = setup_logger('event_logger', 'Events.log')

granted = False
def grant():
    global granted
    granted = True


def login(username, password):
    success = False
    file = open("users.txt", "r")
    for i in file:
        a, b = i.split(",")
        b = b.strip()
        encrypt_password=encrypt(password,4)
        if (a == username and b == encrypt_password):
            success = True
            break
    file.close()
    if (success):
        grant()
    else:
        print("\n********** \033[1m ❌ Wrong username or password, please try again ❌\033[0m **********\n")
        user_type_access("1")



def register_user(username, password):
    file = open("users.txt", "a")
    encrypted_password=encrypt(password,4)
    file.write("\n" + username + "," + encrypted_password)
    file.close()
    print("**********  \033[1m ✅ Successfully registered ✅ \033[0m**********")
    grant()
    present_admin_menu()


def user_type_access(option):
    global username
    if (option == "1"):
        username = input("Enter Username:")
        password = getpass.getpass("Enter Password: ")
        login(username, password)

    if (option == "2"):
        username = input("Enter Username:")
        password = getpass.getpass("Enter Password: ")
        login(username, password)


def begin():
    global o
    print("|--------------------------------------------------------|")
    print("|--------------- \033[1m Welcome to Group 6 DDBMS \033[0m -------------|")
    print("|--------------------------------------------------------|")
    o = input("\033[1m Please, choose the number (1 or 2) that reflects your login type:\033[0m \n 1.User login \n 2.Admin Login  \n")
    if (o != "1" and o != "2"):
        print("\n*********** \033[1m ❌ Invalid Input ❌ \033[0m ***********\n")
        begin()



def present_admin_menu():
    admin_menu = input("\033[1m Please, choose the number that describes your indeed activity:\033[0m \n"
                       " 1.Register a new user \n"
                       " 2.Add a new table \n"
                       " 3.Delete a table \n"
                       " 4.List all tables \n"
                       " 5.View Logs\n"
                       " 6.Export MYSQL dump\n"
                       " 7.Import MYSQL dump\n"
                       " 8.ERD\n"
                       " 9.Exit\n"
                       )
    if (admin_menu == "1"):
        print("Enter user name and password to register")
        username = input("Enter a name:")
        password = getpass.getpass("Enter  a password")
        register_user(username, password)

    if (admin_menu == "2"):
        table_name = input("Enter the Table name:")
        table_location = input("\033[1m Please, choose the server for your table:\033[0m \n"
                          " 1.Server (1) \n"
                          " 2.Server (2) \n")
        column_num = int(input("Enter the \033[1mnumber\033[0m of columns you want to insert:  "))
        column_data = {}
        for i in range(column_num):
            column_name = input("Enter column name:  ")
            column_type = input("Enter column type:  ")
            column_data[column_name] = column_type
        pk_index = input("Enter the \033[1mindex\033[0m of primary key column:  ") or " "
        fk_index = input("Enter the \033[1mindex\033[0m of foreign key column:  ") or " "
        fk_table = input("Enter the \033[1mname\033[0m of reference table:  ") or " "
        fk_column = input("Enter the \033[1mcolunm name\033[0m of reference table:  ") or " "
        
        sendRequest({'type': 'createTable', 'table': table_name, 'columns':column_data, 'keys':{'pk': pk_index, 'fk': fk_index, 'fk_table': fk_table, 'fk_column': fk_column}},table_location)

        print(" \033[1m ✅ Successfully created\033[0m", column_data, "\033[1m columns to table \033[0m", table_name,
                  "✅\n")

    if (admin_menu == "3"):
        table_name = input("Enter the Table name:")
        table_location='0'
        with open("gdd.txt","r") as f_gdd:
            for line in f_gdd:
                line = line.rsplit('=',1)
                if(line[0] == table_name):
                    table_location = line[1].rsplit('\n',1)[0]
            f_gdd.close()
        sendRequest({'type': 'deleteTable', 'table':table_name}, table_location)
            
    if (admin_menu == "4"):
        header = ["Table Name", "Location"]
        rows = []
        table_data = [header]
        with open("gdd.txt", "r") as f_table_read:
            rows = f_table_read.readlines()
            f_table_read.close()
        for line in rows:
            line = line.split("=")
            table_data.append([line[0],"Server: "+line[1].split('\n')[0]])
        print(tabulate(table_data, headers='firstrow', tablefmt='fancy_grid'))

    if (admin_menu == "5"):
        log_choice = input("Please, choose the log to view: \n"
                          " 1.General logs (1) \n"
                          " 2.Event logs (2) \n")
        if(log_choice == "1"):
            with open("General.log", "r") as f_log_read:
                for line in (f_log_read.readlines() [-20:]):
                    print(line, end ='')
                f_log_read.close()
        else:
            with open("Events.log", "r") as f_log_read:
                for line in (f_log_read.readlines() [-20:]):
                    print(line, end ='')
                f_log_read.close()

    if (admin_menu == "6"):

        rows = []
        with open("tables_metadata.txt", "r") as f_table_read:
            rows = f_table_read.readlines()
            f_table_read.close()

        with open("dump/dump.txt", "w") as f_table_write:
            for line in rows:
                f_table_write.write(line)
            f_table_write.close()
        print("Database dump is created in dump/dump.txt")

    if (admin_menu == "7"):

        rows = []
        with open("dump/dump.txt", "r") as f_table_read:
            rows = f_table_read.readlines()
            f_table_read.close()

        for line in rows:
            line = line.split('\n')[0]
            line2 = ast.literal_eval(line)
            sendRequest(line2, line2['server'])

    if (admin_menu == "8"):
        server = input("\033[1m Please, choose the server:\033[0m \n"
                          " 1.Server (1) \n"
                          " 2.Server (2) \n")
        sendRequest({'type': 'erd'},server)

    if (admin_menu == "9"):
        print(" \033[1m Thank you \033[0m")
    else:
        present_admin_menu()


def present_user_menu():
    user_menu = input("\033[1m Please, choose the number that describes your indeed activity:\033[0m \n"
                      " 1.Query \n"
                      " 2.Start Transaction\n"
                      " 3.View Logs\n"
                      " 4.Exit\n"
                      )

    if (user_menu == "1"):
        u_query = input("Please enter the SQL query : ")
        event_logger.info("User query : "+u_query)
        start_time=time.perf_counter()
        try:
            queryParsingResult = processUserQuery(u_query)
        except:
            print("Error in query parsing.")
        table_location='0'
        if(queryParsingResult['table']):
            table_name = queryParsingResult['table']
            with open("gdd.txt","r") as f_gdd:
                for line in f_gdd:
                    line = line.rsplit('=',1)
                    if(line[0] == table_name):
                        table_location = line[1].rsplit('\n',1)[0]
                f_gdd.close()
            queryParsingResult['is_transaction']=0
            sendRequest(queryParsingResult,table_location)
        end_time=time.perf_counter_ns()
        general_logger.info("Processing time for the query : "+u_query+" is "+str(end_time-start_time)+" ns")
        present_user_menu()

    if (user_menu == "2"):
        while(True):
            user_choice = input("\033[1m Please, choose the option for your transaction:\033[0m \n"
                              " 1.Transaction Query (1) \n"
                              " 2.Commit (2) \n")
            if(user_choice == "1"):
                u_query = input("Please enter the SQL query : ")
                try:
                    queryParsingResult = processUserQuery(u_query)
                except:
                    print("Error in query parsing.")
                    sendRequest({'type':'rollback'}, 1)
                table_location='0'
                if(queryParsingResult['table']):
                    table_name = queryParsingResult['table']
                    with open("gdd.txt","r") as f_gdd:
                        for line in f_gdd:
                            line = line.rsplit('=',1)
                            if(line[0] == table_name):
                                table_location = line[1].rsplit('\n',1)[0]
                        f_gdd.close()
                    queryParsingResult['is_transaction']=1
                    result = sendRequest(queryParsingResult,table_location)
                    if(result == 0):
                        break
            else:
                sendRequest({'type': 'commit'},'1')
                break

    if (user_menu == "3"):
        log_choice = input("Please, choose the log to view: \n"
                          " 1.General logs (1) \n"
                          " 2.Event logs (2) \n")
        if(log_choice == "1"):
            with open("General.log", "r") as f_log_read:
                for line in (f_log_read.readlines() [-20:]):
                    print(line, end ='')
                f_log_read.close()
        else:
            with open("Events.log", "r") as f_log_read:
                for line in (f_log_read.readlines() [-20:]):
                    print(line, end ='')
                f_log_read.close()

    if (user_menu == "4"):
        print(" \033[1m Thank you \033[0m")
    else:
        present_user_menu()


begin()
user_type_access(o)
if (granted):

    print("|------------------ \033[1m Welcome", username, "😃 \033[0m ------------------|")
    if (username == "Admin"):
        present_admin_menu()
    else:
        present_user_menu()

