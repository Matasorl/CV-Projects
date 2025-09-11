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


def load_data():
    rides = []
    no_of_people = []
    with open('Queues.txt', 'r') as queuefile:
        for line in queuefile:
            line = line.rstrip().split(',')
            rides.append(line[0])
            no_of_people.append(int(line[1]))
    return rides, no_of_people


def get_longest_queue(ride_list, people_list):
    longest_queue = ''
    queue_no = 0
    for i, x in enumerate(people_list):
        if x > queue_no:
            queue_no = x
            longest_queue = ride_list[i]
    return longest_queue


def close_notice(ride_list, people_list):
    now_list = []
    later_list = []
    soon_list = []
    for i, x in enumerate(people_list):
        if x >= 40:
            now_list.append(ride_list[i])
        elif 40 > x >= 20:
            soon_list.append(ride_list[i])
        elif 20 > x:
            later_list.append(ride_list[i])

    print('Closing now:')
    print(now_list)
    print('Closing in 30 min:')
    print(soon_list)
    print('Closing at 5:')
    print(later_list)


class Painting:
    def __init__(self, name_of_painting, artist_name, artist_surname, price):
        self.name_of_painting = name_of_painting
        self.artist_name = artist_name
        self.artist_surname = artist_surname
        self.price = price

    def __str__(self):
        return f'{self.name_of_painting} {self.artist_name} {self.artist_surname}'

    def get_sale_price(self):
        return f"{self.price * 0.9}"

    def get_initials(self):
        return f"{self.artist_name[0]}.{self.artist_surname[0]}"


def main():
    age, fullname = get_details()
    print_ticket(age, fullname)
    theme_park_numbers()
    ride_list, people_list = load_data()
    longest_queue = get_longest_queue(ride_list, people_list)
    print(f'The longest queue is the {longest_queue}')
    close_notice(ride_list, people_list)


main()
