# Matas Orliukas r00246824
import datetime


def get_details():
    firstname = input("What is your first name >> ")
    surname = input('What is your surname >> ')
    age = int(input('What is your age >> '))
    fullname = f'{firstname[0].upper()}{firstname[1].upper()}{firstname[2].upper()}-{surname[0].upper()}{surname[1].upper()}{surname[2].upper()}'
    return age, fullname


def print_ticket(age, name):
    today = datetime.datetime.now()
    print('Ticket Info')
    print('==========')
    print(f"Name : {name}")
    if age > 18:
        type = 'Adult'
        cost = 20.50
    else:
        type = 'Kids'
        cost = 12.00
    print(f'Type : {type}')
    print(f'Date : {today.day}/{today.month}/{today.year}')
    print(f'Ticket cost : €{cost}')


def main():
    age, fullname = get_details()
    print_ticket(age, fullname)


main()
