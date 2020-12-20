// student.h
#include "human.h"
#include <string>
#include <vector>
class student : public human {
public:
	// ����������� ������ Student
	student(std::string last_name, std::string name, std::string second_name,
		std::vector<int> scores) : human(last_name, name, second_name) {
		this->scores = scores;
	}

	student() : human() {}
	
	// ��������� �������� ����� ��������
	float get_average_score()
	{
		// ����� ���������� ������
		unsigned int count_scores = this->scores.size();
		// ����� ���� ������ ��������
		unsigned int sum_scores = 0;
		// ������� ����
		float average_score;
		for (unsigned int i = 0; i < count_scores; ++i) {
			sum_scores += this->scores[i];
		}
		average_score = (float)sum_scores / (float)count_scores;
		return average_score;
	}


	void getdata()
	{
		human::getdata();
		std::vector<int> scores;
		int mark1, mark2, mark3, mark4, mark5;
		// ���������� ������ �������� � ������
		std::cout << "������� ������ �1 : ";
		std::cin >> mark1;
		scores.push_back(mark1);
		std::cout << "������� ������ �2 : ";
		std::cin >> mark2;
		scores.push_back(mark2);
		std::cout << "������� ������ �3 : ";
		std::cin >> mark3;
		scores.push_back(mark3);
		std::cout << "������� ������ �4 : ";
		std::cin >> mark4;
		scores.push_back(mark4);
		std::cout << "������� ������ �5 : ";
		std::cin >> mark5;
		scores.push_back(mark5);
		this->scores = scores;
		
	}

	 void putdata()
	{
		std::cout << "\nClass student. ";
		human::putdata();
		std::cout << "\nAverage score:" << get_average_score();
	}
private:
	// ������ ��������
	std::vector<int> scores;
};