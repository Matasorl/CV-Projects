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


def theme_park_numbers():
    counter = 0
    adults = [200, 210, 100, 90, 35]
    kids = [150, 200, 207, 108, 203]
    adult_cost = 20.50
    kid_cost = 12.00
    day_1_revenue = adults[0] * adult_cost + kids[0] * kid_cost
    day_2_revenue = adults[1] * adult_cost + kids[1] * kid_cost
    day_3_revenue = adults[2] * adult_cost + kids[2] * kid_cost
    day_4_revenue = adults[3] * adult_cost + kids[3] * kid_cost
    day_5_revenue = adults[4] * adult_cost + kids[4] * kid_cost
    total = day_1_revenue + day_2_revenue + day_3_revenue + day_4_revenue + day_5_revenue

    for i, x in enumerate(adults):
        day_total = adults[i] + kids[i]
        if day_total > 300:
            counter += 1

    day_limit_cost = 150 * adult_cost + 150 * kid_cost
    total_lost = (day_1_revenue - day_limit_cost) + (day_2_revenue - day_limit_cost) + (day_3_revenue - day_limit_cost)

    print('FunTimes Amusement Park')
    print('===========')
    print(f'1: €{day_1_revenue:,.2f}')
    print(f'2: €{day_2_revenue:,.2f}')
    print(f'3: €{day_3_revenue:,.2f}')
    print(f'4: €{day_4_revenue:,.2f}')
    print(f'5: €{day_5_revenue:,.2f}')
    print('=========')
    print(f'Total: €{total}')
    print()
    print(f"The number of days exceeding the 300 limit is {counter}")
    print(f"Revenue lost due to limit: €{total_lost:,.2f}")


def main():
    age, fullname = get_details()
    print_ticket(age, fullname)
    theme_park_numbers()


main()
