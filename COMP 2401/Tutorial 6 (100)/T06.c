#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "t06defs.h"

/* Implement StudentNode type */
typedef struct Node {
  StudentType *data;
  struct Node *next;
} StudentNode;

typedef struct {
  StudentNode *head;
} StudentList;

/* Implement the following functions */
void addStudent(StudentList *, StudentNode *);
void appendStudent(StudentList *, StudentNode *);
void printList(const StudentList *);
void popStudent(StudentList *);

int main(void) {
  
  StudentType matilda, joe, timmy;
  StudentNode matildaNode, joeNode, timmyNode;

  initStudent("Matilda", "Mallister", 22, "123444555",
              9.0, &matilda);
  initStudent("Joe", "The Plumber", 24, "567888999",
              8.7, &joe);
  initStudent("Timmy", "Tortoise", 99, "345667788",
              11.5, &timmy); 
 
  matildaNode.data = &matilda;
  joeNode.data = &joe;
  timmyNode.data = &timmy;

  StudentList comp2401;
  comp2401.head = NULL;

  addStudent(&comp2401, &matildaNode);
  addStudent(&comp2401, &timmyNode);
  addStudent(&comp2401, &joeNode);

  printList(&comp2401);

  printf("---------------------------------------\n");

  StudentList comp2402;
  comp2402.head = NULL;
  
  appendStudent(&comp2402, &matildaNode);
  appendStudent(&comp2402, &timmyNode);
  appendStudent(&comp2402, &joeNode);
  
  //popStudent(&comp2402);

  printList(&comp2402);

  return 0;
}

/*   Function:  addStudent                                  */
/*         in:  Location of StudentList to be modified      */
/*         in:  Location of StudentType to be added         */
/*        out:  StudentList with added StudentType          */
/*    Purpose:  adds stu to stuList at the head of the list */
void addStudent(StudentList *stuList, StudentNode *stuNode) {
  StudentNode *newNode = stuNode;
  newNode->next = stuList->head;
  stuList->head = newNode;
}

void printList(const StudentList *stuList) {
  StudentNode *printNode = stuList->head;
  while(printNode != NULL){
    printStudent(printNode->data);
    printNode = printNode->next;
  }
}

void appendStudent(StudentList *stuList, StudentNode *a){
  StudentNode *currNode;
  StudentNode *prevNode;
  StudentNode *tmpNode;

  tmpNode = a; 
  currNode = stuList->head;
  prevNode = NULL;

  while(currNode != NULL){
    prevNode = currNode;
    currNode = currNode->next;
  }

  if (prevNode == NULL) {
    stuList->head = tmpNode;
  }
  else {
    prevNode->next = tmpNode;
  }

  tmpNode->next = currNode;
  
}

void popStudent(StudentList *stuList){
  if(stuList->head == NULL){
    return; 
  }      
  stuList->head = stuList->head->next;
}
