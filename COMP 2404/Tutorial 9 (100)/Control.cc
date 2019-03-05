#include "Control.h"

Control::Control()
{
	Array fictionarray;
	Array nonfictionarray;
	server.retrieve(fictionarray, nonfictionarray);

	for(int i = 0; i < fictionarray.getSize(); ++i){
		loungelibraryobject.addBook(fictionarray.get(i));
	}

	for(int i = 0; i < nonfictionarray.getSize(); ++i){
		scslibraryobject.addBook(nonfictionarray.get(i));
	}

}

Control::~Control()
{
	Array fictionarray;
	Array nonfictionarray;
	loungelibraryobject.copyBooks(fictionarray);
	scslibraryobject.copyBooks(nonfictionarray);
	server.update(fictionarray, nonfictionarray);
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
				NonFictionBook* book = new NonFictionBook(id, callnumber, title, author, year);
				scslibraryobject.addBook(book);
			}
			if(booktype == "Fiction"){
				FictionBook* book = new FictionBook(id, callnumber, title, author, year);
				loungelibraryobject.addBook(book);
			}
	}
	
	cout << endl << "Lounge Library (Fiction Books):" << endl;
	loungelibraryobject.format(output);
	viewobject.print(output);

	cout << endl << "SCS Library (Non Fiction Books):" << endl;
	output = "";
	scslibraryobject.format(output);
	viewobject.print(output);
	
}