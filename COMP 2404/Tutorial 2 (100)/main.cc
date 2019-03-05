#include <iostream>
#include <string>
#include "Book.h"
#include "Library.h"
using namespace std;

int  mainMenu();

int main()
{  
  Library alexandria;

  while (1) {
    
    int id, year, menuSelection;
    string title, author;
      
    menuSelection = mainMenu();

    if (menuSelection == 0) 
      break;
    else if (menuSelection == 1) {
      cout << "id:  ";
      cin  >> id;
      cout << "title:   ";
      cin.ignore();
      getline(cin, title);
      cout << "author: ";
      getline(cin, author);
      cout << "year:  ";
      cin  >> year;

      Book tempbookobject(id, title, author, year);
      alexandria.addBook(tempbookobject);

    }
  }
    
  alexandria.printLibrary();

  return 0;
}

int mainMenu()
{
  int numOptions = 1;
  int selection  = -1;

  cout << endl;
  cout << "(1) Add book" << endl;
  cout << "(0) Exit" << endl;

  while (selection < 0 || selection > numOptions) {
    cout << "Enter your selection: " << endl;
    cin  >> selection;
  }

  return selection;
}

