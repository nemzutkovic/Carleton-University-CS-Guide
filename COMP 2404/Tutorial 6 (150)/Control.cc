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
	string output;

	while(true){
		menuSelection = viewobject.mainMenu();
		if(menuSelection == 0){
			break;
		}
		viewobject.readInfo(&id, &title, &author, &year);
	    Book* bookobject = new Book(id, title, author, year);
	    libraryobject.addBook(bookobject);
	}

	libraryobject.format(output);
	viewobject.print(output);
}