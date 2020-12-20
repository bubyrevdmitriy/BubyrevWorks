// main.cpp
#include <iostream>
#include <vector>
#include "mesurmentsResult.h"
#include "mesurmentsResultCharlier.h"
#include "mesurmentsResultRomanovsky.h"

using namespace std;

void infoAboutTestWorkMetodology() {
	cout << "Metrology. Gross errors." << endl;
	cout << endl;
	cout << "A gross error, or a miss, is the error of the result of an individual measurement" << endl;
	cout << "included in a series of measurements, which for these conditions differs sharply" << endl;
	cout << "from the rest of the results of this series.For single measurements, detect a miss" << endl;
	cout << "impossible.With multiple measurements to detect misses use statistical criterion:" << endl;
	cout << "Romanovsky, Charlier." << endl;
	cout << endl;
	cout << "This program demonstrates work of two criterias: Romanovsky criteria, Charlier criteria." << endl;
	cout << endl;
	cout << "Romanovsky criterion is applied if the number of measurements is less than 20." << endl;
	cout << "Charlier criterion is applied if the number of measurements is more than 20." << endl;
	cout << endl;
}

int main()
{
	system("chcp 1251");

	/*
	��� ���������� ���� ��������, ����������� ������������ ���������� ��������� � ������� ������ ����������� ��������� ������ � ���� �� ���������.
	������ �������������� �� �������� ������������ � �������� �����. ��������� ���������� ���������� ��� ������� ������:
	1)���������� ��������� 20. ��� ���������: 25,30,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25. ������� ���������� 0,01.  (�������� �������� �������� ������������ ��� ����� ��������� ������ 20)
	2) ���������� ��������� 30. ��� ���������: 25,30,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25 ,25,25,25,25,25,25,25,25,25,25 . (�������� �������� �������� ����� ��� ����� ��������� ������ 20)
	*/

	infoAboutTestWorkMetodology();

	cout << "Please enter how much measurements(3<x) you want to make" << endl;//������� ��������� � ��������� ����� 	
	int measurementsNumber;
	cin >> measurementsNumber;
	double* measurementsTemp = new double[measurementsNumber];
	
	
	cout << "��������� ������ �������" << "\n";
		for (int i = 0; i < measurementsNumber; i++)
		{
			cout << "[Measure �" <<i+1 << "]=";
			cin >> measurementsTemp[i];
		}
	std::vector<double> measurementsArray;
		for (int i = 0; i < measurementsNumber; i++)
		{
			measurementsArray.push_back(measurementsTemp[i]);
		}

		if (measurementsNumber <= 20)
		{
			cout << "Romanovsky criterion" << endl; 
			mesurmentsResultRomanovsky* mesurmentsArrayClass01 = new mesurmentsResultRomanovsky(measurementsNumber, measurementsArray);
			mesurmentsArrayClass01->makeBeatifulMesurmentsResult();

			mesurmentsArrayClass01->set_criterionRomanovsky();
			mesurmentsArrayClass01->set_mesurmentsArrayRomanovskyBeta();
			mesurmentsArrayClass01->set_mesurmentsArrayResult();
			mesurmentsArrayClass01->set_mesurmentsResultResult();
			mesurmentsArrayClass01->print();

			int printChoice;
			cout << "Do you want to export data in file(1 = yes, 0 = no)?" << endl;
			cin >> printChoice;

			if (printChoice == 1)
			{
				string fileName;
				cout << "Type desirable filename(without extension)" << endl;
				cin >> fileName;
				mesurmentsArrayClass01->printToFile(fileName);
			}
		}
		else
		{
			cout << "Charlier criterion" << endl;
			mesurmentsResultCharlier* mesurmentsArrayClass01 = new mesurmentsResultCharlier(measurementsNumber, measurementsArray);
			mesurmentsArrayClass01->makeBeatifulMesurmentsResult();
			
			mesurmentsArrayClass01->set_criterionCharlier();
			mesurmentsArrayClass01->set_KS();
			mesurmentsArrayClass01->set_mesurmentsArrayResult();
			mesurmentsArrayClass01->set_mesurmentsResultResult();
			mesurmentsArrayClass01->print();

			int printChoice;
			cout << "Do you want to export data in file(1 = yes, 0 = no)?" << endl;
			cin >> printChoice;

			if (printChoice == 1)
			{
				string fileName;
				cout << "Type desirable filename(without extension)" << endl;
				cin >> fileName;
				mesurmentsArrayClass01->printToFile(fileName);
			}
		}

	
	return 0;
}




