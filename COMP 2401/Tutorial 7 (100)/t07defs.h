#define MAX_STR  32

#define C_OK   0
#define C_NOK -1

typedef struct {
  char first[MAX_STR];
  char last[MAX_STR];
  int  age;
} PersonType;

typedef struct {
  PersonType basicInfo;
  char  stuNumber[MAX_STR];
  float gpa;
} StudentType;

typedef struct StuNode{
  StudentType *data;
  struct StuNode *next;
} StudentNode;

typedef struct {
  StudentNode *head;
} StudentList;

void printList(const StudentList *);
void printStudent(const StudentType*);
int initStudent(char *f, char *l, int a,
                 char *n, float g, StudentType **stuPtr);
