#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "t07defs.h"

void printList(const StudentList *stuList) {
  StudentNode *currNode = stuList->head;
  
  while (currNode != NULL) {
    printStudent(currNode->data);
    currNode = currNode->next;
  }
}

void printStudent(const StudentType *stu)
{
  printf("Here's %s! %s %s is this smart: %.1f\n",
         stu->basicInfo.first, 
         stu->basicInfo.first, stu->basicInfo.last,
         stu->gpa);
}

int initStudent(char *f, char *l, int a,
                 char *n, float g, StudentType **stuPtr)
{
  if (f == NULL || l == NULL || n == NULL || stuPtr == NULL) {
    return C_NOK;
  }

  *stuPtr = malloc(sizeof(StudentType));

  strcpy((*stuPtr)->basicInfo.first, f);
  strcpy((*stuPtr)->basicInfo.last, l);
  (*stuPtr)->basicInfo.age = a;
  strcpy((*stuPtr)->stuNumber, n);
  (*stuPtr)->gpa = g;

  return C_OK;
}

