#include "View.h"

int View::mainMenu()
{
  int numOptions = 1;
  int selection  = -1;

  cout << endl;
  cout << "(1) Add event" << endl;
  cout << "(0) Exit" << endl;

  while (selection < 0 || selection > numOptions) {
    cout << "Enter your selection: " << endl;
    cin  >> selection;
  }

  return selection;
}

void View::readInfo(string* e, int* d, int* m, int* y, int* h, int* mi, int* s)
{
  cout << "Event: ";
  cin.ignore();
  getline(cin, *e);
  cout << "Day: ";
  cin  >> *d;
  cout << "Month: ";
  cin  >> *m;
  cout << "Year: ";
  cin  >> *y;
  cout << "Hours: ";
  cin  >> *h;
  cout << "Minutes: ";
  cin  >> *mi;
  cout << "Seconds: ";
  cin  >> *s;
}

void View::print(string* eventstring)
{
  cout << *eventstring;
}