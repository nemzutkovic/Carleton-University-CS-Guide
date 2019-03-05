/* * * * * * * * * * * * * * * * * * * * * * * * * */
/*                                                 */
/*  Program:  Simple Event Management System       */
/*  Author:   Christine Laurendeau				         */
/*  Date:     07-JUN-2018                          */
/*                                                 */
/*  (c) 2018 Christine Laurendeau                  */
/*  All rights reserved.  Distribution and         */
/*  reposting, in part or in whole, without the    */
/*  written consent of the author, is illegal.     */
/*                                                 */
/* * * * * * * * * * * * * * * * * * * * * * * * * */

#include <iostream>
using namespace std;
#include <string>

#include "Event.h"
#include "Calendar.h"

int  mainMenu();
void printCalendar(Date arr[MAX_ARR_SIZE], int);

int main()
{
  Calendar   cal("Life Schedule");

  int    	 day, month, year, hours, minutes, seconds, menuSelection;
  int      eventcounter = 0;
  string	 event;

  while (1) {

    menuSelection = mainMenu();

    if (menuSelection == 0) 
      break;
    else if (menuSelection == 1) {
      cout << "Event: ";
      cin.ignore();
      getline(cin, event);
      cout << "Day: ";
      cin  >> day;
      cout << "Month: ";
      cin  >> month;
      cout << "Year: ";
      cin  >> year;
      cout << "Hours: ";
      cin  >> hours;
      cout << "Minutes: ";
      cin  >> minutes;
      cout << "Seconds: ";
      cin  >> seconds;
      
      Event* eventobject = new Event(event, day, month, year, hours, minutes, seconds);
      cal.add(eventobject);
      ++eventcounter;
    }
  }
    cal.printEvents();

  return 0;
}

int mainMenu()
{
  int numOptions = 1;
  int selection  = -1;

  cout << endl;
  cout << "(1) Add event" << endl;
  cout << "(0) Exit" << endl;

  while (selection < 0 || selection > numOptions) {
    cout << "Enter your selection: ";
    cin  >> selection;
  }

  return selection;
}