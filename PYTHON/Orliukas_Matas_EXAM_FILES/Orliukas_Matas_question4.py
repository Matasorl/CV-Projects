from Orliukas_Matas_q4 import Painting


def main():
    painting1 = Painting('Blue Skies', 'Ann', 'Roberts', '900')
    painting2 = Painting('Red Sunset', 'Ben', 'Tate', '500')

    print(f"{painting1.name_of_painting} {painting1.artist_name} {painting1.artist_surname} €{painting1.price}")
    print(f"{painting2.name_of_painting} {painting2.artist_name} {painting2.artist_surname} €{painting2.price}")

main()
