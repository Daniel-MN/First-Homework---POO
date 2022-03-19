Musuroi Daniel-Nicusor
313CB

                                            Tema 1
                    LibroData – Gesiunea și livrarea de cărți electronice

	Tema este compusa din clasele impuse (fiecare clasa cu un fisier separat) si o clasa
definita de mine, Database, in care se gasesc metodele pentru citirea fisierelor .in. Fiecare
clasa este definita in fisierul propriu.
	Clasa Book prezinta doua metode de publish(Publish si PublishInBlock). Metoda 
PublishInBlock este folosita la celelalte metode de Publish(pentru EditorialGroup si
PublishingBrand). 
	Clasa Database prezinta 7 atribute HashMap pentru fiecare entitate din LibroData.
Astfel, in constructorul acestei clase se apeleaza functiile de citire din fisiere.
	In clasa Administration sunt implementate cele 5 metode ce descriu functionalitatile
LibroData si cate o metoda de afisare a listei intoarse.

	getBooksForPublishingRetailerID - obtine retailerul din database, apoi parcurge lista
publishingArtifacts specifica retailerului. Se verifica ce fel de IPublishinigArtifact este 
fiecare element si se adauga cartile la lista de carti ce trebuie intoarsa de functie. Pentru
a nu avea dublicate, se verifica daca cartea a mai fost adaugata in lista.

	getLanguagesForPublishingRetailerID - obtine retailerul din database, apoi asemanator
ca la metoda precedenta, se adauga limba specifica fiecarei cartii la o lista ce trebuie
intoarsa.
	
	getCountriesForBookID - obtine cartea din database, apoi se verifica fiecare retailer
daca publica cartea respectiva si daca da, tarile in care o publica sunt adaugate la lista
de tari intoarsa de functie doar daca ele nu au mai fost adaugate inainte.

	getCommonBooksForRetailerIDs - se obtin cei doi retaileri, apoi la un HashMap se 
adauga cartile de la primul retailer(cu ajutorul metodei addBooksRetailer). In continuare,
se parcurg cartile celui de-al doilea retailer si daca acestea se gasesc in HashMap, atunci 
sunt carti comune si se adauga la lista de carti intoarsa. De asemenea, se verifica daca o 
carte a mai fost adaugata la aceasta lista.

	getAllBooksForRetailerIDs - se obtin cei doi retaileri, apoi la un HashMap se adauga
cartile primului retailer si cartile celui de-al doilea retailer. Astfel, se obtin cartile 
celor doi retaileri fara dublicate.

	In cazul in care la teste nu sunt introduse ID-uri corecte, atunci sunt afisate
mesaje specifice.