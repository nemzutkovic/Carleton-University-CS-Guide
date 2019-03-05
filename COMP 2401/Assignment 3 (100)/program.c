#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define C_OK   	 0
#define C_NOK 	-1
#define MAX_STR	64

typedef struct {
  char	title[MAX_STR];
  char	author[MAX_STR];
  int 	year;
} BookType;

typedef struct Node {
  BookType 		*book;
  struct Node *next;
} NodeType;

typedef struct {
	NodeType *head;
	NodeType *tail;
} ListType;

int  initBook(BookType**);
void initList(ListType*);
void addByTitle(ListType*, BookType*);
void addByYear(ListType*, BookType*);
void copyList(ListType*, ListType*);
void copyByYear(ListType*, ListType*);
void delAuthor(ListType*, char*);
void printList(ListType*);
void cleanupList(ListType*);
void cleanupData(ListType*);

int main(){
	ListType booksByTitle;
	ListType booksByYear;
  ListType tmpList;
  BookType *newBook;
  char  	 author[MAX_STR];

  initList(&booksByTitle);
  initList(&booksByYear);
  initList(&tmpList);

  while (initBook(&newBook) != -1) {
    addByTitle(&booksByTitle, newBook);
  }

  printf("\n*** BOOK LIST BY TITLE ***\nBOOK LIST:\n");
  printList(&booksByTitle);
  copyList(&booksByTitle, &tmpList);

  printf("ENTER BOOK AUTHOR TO DELETE: ");
  fgets(author, sizeof(author), stdin);
  printf("%s", author);
  author[strlen(author)-1] = '\0';
  delAuthor(&tmpList, author);
  
  printf("\n*** BOOK LIST BY TITLE (UNALTERED) ***\nBOOK LIST:\n");
	printList(&booksByTitle);
	printf("\n*** TEMP LIST BY TITLE (ALTERED) ***\nBOOK LIST:\n");
  printList(&tmpList);

  copyByYear(&booksByTitle, &booksByYear);
  printf("\n*** BOOK LIST BY TITLE ***\nBOOK LIST:\n");
	printList(&booksByTitle);
	printf("\n*** BOOK LIST BY YEAR ***\nBOOK LIST:\n");
	printList(&booksByYear);

  cleanupData(&booksByTitle);
  cleanupList(&booksByTitle);
	cleanupList(&tmpList);
	cleanupList(&booksByYear);

	return C_OK;
}

/*
  Function:  initBook
  Purpose:   returns a new book
  Parameters:
    out:     book
    return:  success or failure
*/

int initBook(BookType **book){
	char  booktitle[MAX_STR];
  char  bookauthor[MAX_STR];
  char  numberstring[MAX_STR];
  int 	bookyear;
	
	printf("Enter book title: ");
	fgets(booktitle, sizeof(booktitle), stdin);
	booktitle[strlen(booktitle)-1] = '\0';
	if (strcmp(booktitle, "end") == 0)
    return C_NOK;
  
  printf("Enter book author: ");
  fgets(bookauthor, sizeof(bookauthor), stdin);
  bookauthor[strlen(bookauthor)-1] = '\0';
  
  printf("Enter book year: ");
  fgets(numberstring, sizeof(numberstring), stdin);
  numberstring[strlen(numberstring)-1] = '\0';
  sscanf(numberstring, "%d", &bookyear);
  // If the book year is negative, it is set to year 0.
  if(bookyear < 0){
    bookyear = 0;
  }

 	*book = malloc(sizeof(BookType));
 	strcpy((*book)->title, booktitle);
 	strcpy((*book)->author, bookauthor);
 	(*book)->year = bookyear;

  return C_OK;
}

/*
  Function:  initList
  Purpose:   initializes fields of a given list
  Parameters:
    out:   	 list
    return:  none
*/

void initList(ListType *list){
	list->head = NULL;
	list->tail = NULL;
}

/*
  Function:  addByTitle
  Purpose:   adds a new book to the list in ascending alphabetical order
  Parameters:
    inout:   list
    in:			 newBook
    return:  none
*/

void addByTitle(ListType *list, BookType *newBook){
	NodeType* tmpNode = malloc(sizeof(NodeType));
  NodeType* currNode = list->head;
  NodeType* prevNode = NULL;

  tmpNode->book = newBook;
  tmpNode->next = NULL;

  while (currNode != NULL) {
    if (strcmp(newBook->title,currNode->book->title) < 0)
      break;
    prevNode = currNode;
    currNode = currNode->next;
  }

  if (prevNode == NULL) {
    list->head = tmpNode;
  }
  else {
    prevNode->next = tmpNode;
  }

  tmpNode->next = currNode;
  
  if(tmpNode->next == NULL){
    list->tail = tmpNode;
  }
}

/*
  Function:  addByYear
  Purpose:   adds a new book to the list in descending order by year
  Parameters:
    inout:   list
    in:			 newBook
    return:  none
*/

void addByYear(ListType *list, BookType *newBook){
	NodeType* tmpNode = malloc(sizeof(NodeType));
  NodeType* currNode = list->head;
  NodeType* prevNode = NULL;

  tmpNode->book = newBook;
  tmpNode->next = NULL;

  while (currNode != NULL) {
    if (newBook->year > currNode->book->year)
      break;
    prevNode = currNode;
    currNode = currNode->next;
  }

  if (prevNode == NULL) {
    list->head = tmpNode;
  }
  else {
    prevNode->next = tmpNode;
  }

  tmpNode->next = currNode;
  
  if(tmpNode->next == NULL){
    list->tail = tmpNode;
  }
}

/*
  Function:  copyList
  Purpose:   adds each book in the src list to the dsc list, in ascending order by title
  Parameters:
    in:   	 src
    inout:	 dest
    return:  none
*/

void copyList(ListType *src, ListType *dest){
	NodeType *sourcenode = src->head;
	while(sourcenode != NULL){
		addByTitle(dest, sourcenode->book);
		sourcenode = sourcenode->next;
	}
}

/*
  Function:  copyByYear
  Purpose:   adds each book in the src list to the dsc list, in descending order by year
  Parameters:
    in:   	 src
    inout:	 dest
    return:  none
*/

void copyByYear(ListType *src, ListType *dest){
	NodeType *sourcenode = src->head;
	while(sourcenode != NULL){
		addByYear(dest, sourcenode->book);
		sourcenode = sourcenode->next;
	}
}

/*
  Function:  delAuthor
  Purpose:   deletes all books by the author specified (name) from list
  Parameters:
    inout:   list
    in:	 		 name
    return:  none
*/

void delAuthor(ListType *list, char *name){
  NodeType *currNode = list->head;
  NodeType *prevNode = NULL;

  while(currNode != NULL && (strcmp(currNode->book->author, name) == 0)){
  	list->head = currNode->next;
  	free(currNode);
  	currNode = list->head;
  }

  while(currNode != NULL){
  	while(currNode != NULL && (strcmp(currNode->book->author, name) != 0)){
  		prevNode = currNode;
  		currNode = currNode->next;
  	}
  	if(currNode == NULL)
  		break;
  	prevNode->next = currNode->next;
  	if(prevNode->next == NULL)
  		list->tail = prevNode;
  	free(currNode);
  	currNode = prevNode->next;

  }
}

/*
  Function:  printList
  Purpose:   prints all the books contained in list and denotes what the head/tail of the list is
  Parameters:
    out:     list
    return:  none
*/

void printList(ListType *list){
	  NodeType* currNode = list->head;
	  if(currNode == NULL){
	  	printf("--                                    THE BOOK LIST IS EMPTY\n");
	  	printf("HEAD is: --                                             NULL\n");
	    printf("TAIL is: --                                             NULL\n");
	  }
	  else{
	    while(currNode != NULL){
	    	printf("--  %45s by %15s, Yr: %1d\n", currNode->book->title, currNode->book->author, currNode->book->year);
	      currNode = currNode->next;
	    }
	    printf("HEAD is: -- %50s by %15s, Yr: %1d\n", list->head->book->title, list->head->book->author, list->head->book->year);
	    printf("TAIL is: -- %50s by %15s, Yr: %1d\n", list->tail->book->title, list->tail->book->author, list->tail->book->year);
  	}
}

/*
  Function:  cleanupList
  Purpose:   frees the memory associated with the nodes in the list
  Parameters:
    out:     list
    return:  none
*/

void cleanupList(ListType *list){
	NodeType *currNode, *nextNode;
  currNode = list->head;
 
  while (currNode != NULL) {
    nextNode = currNode->next;
    free(currNode);
    currNode = nextNode;
  }
}

/*
  Function:  cleanupData
  Purpose:   frees the memory associated with the books in the list
  Parameters:
    out:     list
    return:  none
*/

void cleanupData(ListType* list){
  NodeType *currNode = list->head;
 
  while (currNode != NULL) {
    free(currNode->book);
    currNode = currNode->next;
  }
}