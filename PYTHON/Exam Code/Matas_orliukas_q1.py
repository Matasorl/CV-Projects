# Matas Orliukas    R00246824

SINGLE = 100
DOUBLE = 195
FAMILY_ADULT = 100
FAMILY_CHILD = 50
DISCOUNT = 0.15
DISCOUNT_CODE = 'BREAK15'


contact_email = input(f"Please provide a contact email >>> ")

menu = input(f"Select a room:\n" 
             f"1: Single\n" 
             f"2: Double\n" 
             f"3: Family\n")
discount_input = input(f"Input a discount code (press ENTER if you have none) ")


if menu == '1':
    cost = SINGLE
    if discount_input == DISCOUNT_CODE:
        cost = cost * (1 - DISCOUNT)
    print(f"{'Email':10}{'Room':<10}{'Cost':20}")
    print('=' * 40)
    print(f"{contact_email:10}{'Single':30}{cost:>10.2f}", end='')

elif menu == '2':
    cost = DOUBLE
    if discount_input == DISCOUNT_CODE:
        cost = cost * (1 - DISCOUNT)
    print(f"{'Email':10}{'Room':<10}{'Cost':20}")
    print('=' * 40)
    print(f"{contact_email:10}{'Double':<10}{cost:>10.2f}", end='')

elif menu == '3':
    while True:
        adult_num = int(input(f"How many adults? "))
        child_num = int(input(f"How many children? "))
        if adult_num + child_num < 4:
            break
        else:
            print('A family room cannot hav more than 4 people.')

    cost = FAMILY_ADULT * adult_num + FAMILY_CHILD * child_num
    if discount_input == DISCOUNT_CODE:
        cost = cost * (1 - DISCOUNT)
    print(f"{'Email':10}{'Room':<10}{'Cost':20}")
    print('=' * 40)
    print(f"{contact_email:10}{'Family':<10}{cost:>10.2f}", end='')
