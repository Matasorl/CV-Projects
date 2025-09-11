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
booking_before_discount = 0

output = open('gmail.txt', 'a')

number_of_bookings = int(input(f"Number of bookings? "))
for i in range(number_of_bookings):
    contact_email = input(f"Please provide a contact email >>> Email #{number_email}: ")

    menu = int(input(f"Select a room:\n" 
                     f"1: Single\n" 
                     f"2: Double\n" 
                     f"3: Family\n"))
    discount_input = input(f"Input a discount code (press ENTER if you have none) ")

    if menu == 1:
        cost = SINGLE
        booking_before_discount += cost
        if discount_input == DISCOUNT_CODE:
            cost = cost * (1 - DISCOUNT)
        bookings += f"{contact_email:10}{'Single':<10}{cost:>10.2f}\n"
        total_booking += cost

    elif menu == 2:
        cost = DOUBLE
        booking_before_discount += cost
        if discount_input == DISCOUNT_CODE:
            cost = cost * (1 - DISCOUNT)
        bookings += f"{contact_email:10}{'Double':<10}{cost:>10.2f}\n"
        total_booking += cost

    elif menu == 3:
        while True:
            adult_num = int(input(f"How many adults? "))
            child_num = int(input(f"How many children? "))
            if adult_num + child_num < 4:
                break
            else:
                print('A family room cannot hav more than 4 people.')
        cost = FAMILY_ADULT * adult_num + FAMILY_CHILD * child_num
        booking_before_discount += cost
        if discount_input == DISCOUNT_CODE:
            cost = cost * (1 - DISCOUNT)
        bookings += f"{contact_email:10}{'Family':<10}{cost:>10.2f}\n"
        total_booking += cost
    number_email += 1
average = total_booking / (number_email - 1)
total_value_discounts = booking_before_discount - total_booking
print(f"{'Email':10}{'Room':<10}{'Cost':20}")
print('=' * 40)
print(bookings)
print(f'The average cost of a booking was {average:.2f}')
print(f"Cost of BREAK15 {total_value_discounts:.2f}")
output.close()
