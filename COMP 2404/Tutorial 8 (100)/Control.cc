#include "Control.h"

Control::Control()
{
}

void Control::launch()
{
	int menuSelection;
	int id;
	int year;
	string title;
	string author;
	string callnumber;
	string booktype;
	string output;

	while(true){
		menuSelection = viewobject.mainMenu();
		if(menuSelection == 0){
			break;
		}
		viewobject.readInfo(&id, &title, &author, &year, &callnumber);
		viewobject.readBookType(&booktype);
			if(booktype == "Non-Fiction"){
				NonFictionBook* book = new NonFictionBook(id, title, author, year, callnumber);
				scslibraryobject.addBook(book);
			}
			if(booktype == "Fiction"){
				FictionBook* book = new FictionBook(id, title, author, year, callnumber);
				loungelibraryobject.addBook(book);
			}
	}
	
	cout << "SCS Library (Non Fiction Books):" << endl;
	scslibraryobject.format(output);
	viewobject.print(output);

	cout << "Lounge Library (Fiction Books):" << endl;
	output = "";
	loungelibraryobject.format(output);
	viewobject.print(output);
}