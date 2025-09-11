# Matas Orliukas    R00246824

SINGLE = 100
DOUBLE = 195
FAMILY_ADULT = 100
FAMILY_CHILD = 50
DISCOUNT = 0.15
DISCOUNT_CODE = 'BREAK15'
number_email = 1
bookings = ''
total_booking = 0

output = open('gmail.txt', 'a')

number_of_bookings = int(input(f"Number of bookings? "))
for i in range(number_of_bookings):
    while True:
        contact_email = input(f"Please provide a contact email >>> Email #{number_email}: ")
        if '@' in contact_email and '.' in contact_email:
            break
        else:
            print("Email must contain @ and . for email to be valid")
    while True:
        menu = int(input(f"Select a room:\n" 
                         f"1: Single\n" 
                         f"2: Double\n" 
                         f"3: Family\n"))
        if 3 >= menu >= 1:
            break
        else:
            print("Select from 1 to 3")
    discount_input = input(f"Input a discount code (press ENTER if you have none) ")

    if menu == 1:
        cost = SINGLE
        if discount_input == DISCOUNT_CODE:
            cost = cost * (1 - DISCOUNT)
        bookings += f"{contact_email:10}{'Single':<10}{cost:>10.2f}\n"
        total_booking += cost

    elif menu == 2:
        cost = DOUBLE
        if discount_input == DISCOUNT_CODE:
            cost = cost * (1 - DISCOUNT)
        bookings += f"{contact_email:10}{'Double':<10}{cost:>10.2f}\n"
        total_booking += cost

    elif menu == 3:
        adult_num = int(input(f"How many adults? "))
        child_num = int(input(f"How many children? "))
        while True:
            if adult_num + child_num < 4:
                break
            else:
                print("A family room cannot have more than 4 people")
        cost = FAMILY_ADULT * adult_num + FAMILY_CHILD * child_num
        if discount_input == DISCOUNT_CODE:
            cost = cost * (1 - DISCOUNT)
        bookings += f"{contact_email:10}{'Family':<10}{cost:>10.2f}\n"
        total_booking += cost
    number_email += 1
average = total_booking / (number_email - 1)
print(f"{'Email':10}{'Room':<10}{'Cost':20}")
print('=' * 40)
print(bookings)
print(f'The average cost of a booking was {average:.2f}')
output.close()
