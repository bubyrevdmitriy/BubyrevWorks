// main.cpp
#include <iostream>
#include <vector>
#include "human.h"
#include "student.h"
#include "teacher.h"

using namespace std;
int main()
{
	system("chcp 1251");

	std::cout << "Тестирование работы класса student" << std::endl;
	// Оценкистудента
	std::vector<int> scores;
	// Добавление оценок студента в вектор
	scores.push_back(5);
	scores.push_back(3);
	scores.push_back(4);
	scores.push_back(4);
	scores.push_back(5);
	scores.push_back(3);
	scores.push_back(3);
	scores.push_back(3);
	scores.push_back(3);
	
	student* stud = new student("Петров", "Иван", "Алексеевич", scores);

	std::cout << stud->get_full_name() << std::endl;

	std::cout << "Средний балл : " << stud->get_average_score() << std::endl;
	
	
	std::cout << "Тестирование работы класса teacher" << std::endl;
	
	unsigned int teacher_work_time = 40;
	teacher* tch = new teacher("Сергеев", "Дмитрий", "Сергеевич", teacher_work_time);
	std::cout << tch->get_full_name() << std::endl;
	std::cout << "Количество часов: " << tch->get_work_time() << std::endl;
	
	std::cout << "Тестирование работы классов student и teacher с применением полиморфизма" << std::endl;
	//SetConsoleOutputCP(1251);
	human* pubarr[100];
	int n = 0;
	char choice;
	do
	{
		cout << "\nВводить данные для student или teacher(s / t) ? ";
		cin >> choice;
		

		if (choice == 's') {
		
			pubarr[n] = new student();
		}
		else {
			
			pubarr[n] = new teacher();
		}
		pubarr[n++]->getdata();
		cout << "Продолжать((у / n) ? ";
		cin >> choice;
	} while (choice == 'y');

	for (int j = 0; j < n; j++) //цикл по всем объектам
		 pubarr[j]->putdata(); //вывести данные о публикации
	cout << endl;


	return 0;
}

