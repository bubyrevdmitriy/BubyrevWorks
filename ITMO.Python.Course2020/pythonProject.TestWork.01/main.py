def returnList(address):
    information = []
    with open(address) as csvfile:
        count = 0
        inputcsv = csv.reader(csvfile, delimiter=',')
        for i in inputcsv:
            if (count == 0):
                curLin = i
                information.append(curLin)
                # print(informationHeaders)
            else:
                curLin = i
                # print(curLin)
                information.append(curLin)
            count = count + 1
    # In[ ]:
    return information
# Конец функции
def reformat(s):
    ss = s.strip().split('\n')
    tb = [i.strip().split('\t') for i in ss]
    length = max(max(len(w) for w in words) for words in tb) + 4
    return '\n'.join(''.join(f'{w:{length}}' for w in words) for words in tb)


# Импорт функций:
import csv
import os
import operator
# Начало программы:
print("Hello!")
print()
print("     1. Add")
print("     2. Show all")
print("     3. Show for date")
print("     4.Show by category")
print("     5.Show by min->max")
print("     6. Delete")
print("     0. Exit")
print()
# Исходная информация:
address = 'D:\data\orderdata_Bubyrev.csv'
informationHeaders = ['Category', 'Product', 'Cost', 'Date']
# Цикл:
while True:
    try:
        x = int(input("What do you like to do?"))
        if x == 1:
            print("Action: 1. Add")
            Category = (input("Category:"))
            Product = (input("Product:"))
            Cost = (input("Cost:"))
            Date = (input("Date(Example: 2018-02-02):"))
            # Запись в файл:
            information = returnList(address)
            # Запись в файл:
            with open(address, mode='w', newline='') as csvfile:
                fieldnames = informationHeaders
                writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
                for i in information:
                    writer.writerow({'Category': i[0], 'Product': i[1], 'Cost': i[2], 'Date': i[3]})
                writer.writerow({'Category': Category, 'Product': Product, 'Cost': Cost, 'Date': Date})
            # Конец
            print("Thanks! Have a good day!")
        elif x == 2:
            print("Action: 2. Show all")
            print(informationHeaders[0].ljust(15), informationHeaders[1].ljust(15), informationHeaders[2].ljust(15), informationHeaders[3].ljust(15))
            information = returnList(address)
            for i in information:
                print(i[0].ljust(15), i[1].ljust(15), i[2].ljust(15), i[3].ljust(15))

        elif x == 3:
            print("Action: 3. Show for date")
            reqDate = (input("Chose date:"))
            information = returnList(address)

            for i in information:
                if (i[3] == reqDate):
                    print(i)

        elif x == 4:
            print("Action: 4.Show by category")
            reqCategory = (input("Chose category:"))
            information = returnList(address)

            for i in information:
                if (i[0] == reqCategory):
                    print(i)

        elif x == 5:
            print("Action: 5.Show by min->max")
            information = returnList(address)
            sort = sorted(information, key=operator.itemgetter(2))
            print(informationHeaders[0].ljust(15), informationHeaders[1].ljust(15), informationHeaders[2].ljust(15), informationHeaders[3].ljust(15))
            for i in information:
                print(i[0].ljust(15), i[1].ljust(15), i[2].ljust(15), i[3].ljust(15))

        elif x == 6:
            print("Action: 6. Delete")
            ## Try to delete the file
            try:
                os.remove(address)
                print("The file: {} is deleted!".format(address))
            except OSError as e:
                print("Error: {} - {}!".format(e.filename, e.strerror))

            print("Information delete!")
        elif x == 0:
            print("Action: 0. Exit")
            break
        else:
            print("Oops!  That was no valid number.  Try again...")
    except ValueError:
        print("Oops!  That was no valid number.  Try again...")
# Конец программы: