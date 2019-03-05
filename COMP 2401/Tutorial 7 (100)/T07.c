#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "t07defs.h"

/* Implement the following functions */
int insertStudentAlpha(StudentList *, StudentType *);
int deleteStudent(StudentList *, char *, char *);
int cleanupList(StudentList *);

int main(void) {
  int rc;
  
  StudentType *matilda, *joe, *timmy, *ravi, *charlize;

  initStudent("Matilda", "Mallister", 22, "123444555", 9.0, &matilda);
  initStudent("Joe", "The Plumber", 24, "567888999", 8.7, &joe);
  initStudent("Timmy", "Tortoise", 99, "345667788", 11.5, &timmy); 
  initStudent("Ravi", "Gupta", 35, "123456789", 11.6, &ravi);
  initStudent("Charlize", "Theron", 44, "098765432", 6.5, &charlize);
 
  StudentList *comp2401 = malloc(sizeof(StudentList));

  comp2401->head = NULL;

  insertStudentAlpha(comp2401, matilda);
  insertStudentAlpha(comp2401, joe);
  insertStudentAlpha(comp2401, timmy);
  insertStudentAlpha(comp2401, ravi);
  insertStudentAlpha(comp2401, charlize);
  printList(comp2401);

  rc = deleteStudent(comp2401, "Tommy", "Tortoise");
  printf("%d\n", rc);
  rc = deleteStudent(comp2401, "Joe", "The Plumber");
  printf("%d\n", rc);

  printList(comp2401);
  deleteStudent(comp2401, "Matilda", "Mallister");
  printf("\n");
  printList(comp2401);

  cleanupList(comp2401);
  free(comp2401);

  return 0;
}

int insertStudentAlpha(StudentList *stuList, StudentType *stu) {
  StudentNode *currNode;
  StudentNode *prevNode;
  StudentNode *tmpNode;

  tmpNode = malloc(sizeof(StudentNode));
  tmpNode->data = stu;
  tmpNode->next = NULL;
  currNode = stuList->head;
  prevNode = NULL;

  while(currNode != NULL){
    if((strcmp(tmpNode->data->basicInfo.last,currNode->data->basicInfo.last)) < 0)
      break;
    prevNode = currNode;
    currNode = currNode->next;
  }

  if (prevNode == NULL) {
    stuList->head = tmpNode;
  }
  else {
    prevNode->next = tmpNode;
    tmpNode->next = currNode;
  }

  tmpNode->next = currNode;

  return C_OK;
}

int deleteStudent(StudentList *stuList, char *fname, char *lname) {
  StudentNode *currNode, *prevNode;

  prevNode = NULL;
  currNode = stuList->head;

  while (currNode != NULL) {
    if(((strcmp(lname,currNode->data->basicInfo.last)) == 0) &&
      ((strcmp(fname,currNode->data->basicInfo.first)) == 0))
      break;
    prevNode = currNode;
    currNode = currNode->next;
  }

  if (currNode == NULL)
    return C_NOK;

  if (prevNode == NULL)
    stuList->head = currNode->next;
  else
    prevNode->next = currNode->next;

  free(currNode->data);
  free(currNode);

  return C_OK;
}

int cleanupList(StudentList *stuList) {
  StudentNode *currNode, *nextNode;

  currNode = stuList->head;
 
  while (currNode != NULL) {
    nextNode = currNode->next;
    free(currNode->data);
    free(currNode);
    currNode = nextNode;
  }
  return C_OK;
}