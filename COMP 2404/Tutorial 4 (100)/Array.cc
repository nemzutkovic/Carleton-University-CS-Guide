#include <iostream>
#include "Array.h"

Array::Array()
{
    size = 0;
}

Array::~Array()
{
    for(int i = 0; i < size; ++i){
    	delete elements[i];
    }
}

void Array::add(Book* tempbook)
{
    if(size == 0){
        elements[size] = tempbook;
        ++size;   	
    } 
    else{
	 	for(int i = size-1; i >= 0; i--){
	    	if(!elements[i]->lessThan(tempbook)){
	 			elements[i+1] = elements[i];
                elements[i] = tempbook;
	    	}
	    	else{
	    		elements[i+1] = tempbook;
                break;
	    	}
		}
        ++size;   	
    }
}

void Array::printBooks()
{
    if(size > 0){
        for(int i = 0; i < size; ++i){
            elements[i]->print();
        }
    }
}
