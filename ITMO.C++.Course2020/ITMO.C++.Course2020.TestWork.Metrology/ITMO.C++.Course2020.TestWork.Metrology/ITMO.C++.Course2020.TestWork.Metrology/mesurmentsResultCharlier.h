// student.h
#include "mesurmentsResult.h"
#include <string>
#include <vector>
#include <sstream>
#include<fstream>
using namespace std;
class mesurmentsResultCharlier : public mesurmentsResult {
public:
	mesurmentsResultCharlier(int numberOfMesurments, std::vector<double> mesurmentsArrayEnter) : mesurmentsResult(numberOfMesurments, mesurmentsArrayEnter)
	{}

	void set_criterionCharlier() {
		double criterionCharlier;

		if (numberOfMesurments <= 5) { criterionCharlier = 1.3; }//1
		if (5 < numberOfMesurments && numberOfMesurments < 10) { criterionCharlier = (numberOfMesurments - 5) * (1.65 - 1.3) / (10 - 5); }//2
		if (numberOfMesurments == 10) { criterionCharlier = 1.65; }//3
		if (10 < numberOfMesurments && numberOfMesurments < 20) { criterionCharlier = (numberOfMesurments - 10) * (1.96 - 1.65) / (20 - 10); }//4
		if (numberOfMesurments == 20) { criterionCharlier = 1.96; }//5
		if (20 < numberOfMesurments && numberOfMesurments < 30) { criterionCharlier = (numberOfMesurments - 20) * (2.13 - 1.96) / (30 - 20); }//6
		if (numberOfMesurments == 30) { criterionCharlier = 2.13; }//7
		if (30 < numberOfMesurments && numberOfMesurments < 40) { criterionCharlier = (numberOfMesurments - 30) * (2.24 - 2.13) / (40 - 30); }//8
		if (numberOfMesurments == 40) { criterionCharlier = 2.24; }//9
		if (40 < numberOfMesurments && numberOfMesurments < 50) { criterionCharlier = (numberOfMesurments - 40) * (2.32 - 2.24) / (50 - 40); }//10
		if (numberOfMesurments == 50) { criterionCharlier = 2.32; }//11
		if (50 < numberOfMesurments && numberOfMesurments < 100) { criterionCharlier = (numberOfMesurments - 50) * (2.58 - 2.32) / (100 - 50); }//12
		if (numberOfMesurments >= 100) { criterionCharlier = 2.58; }//13

		this->criterionCharlier = criterionCharlier;
	}

	void set_KS() {
		double KS;
		KS = criterionCharlier * standardDeviation;
		this->KS = KS;
	}

	void set_mesurmentsArrayResult() {
		std::vector<double> mesurmentsArrayResult;
		int numberOfMesurmentsResult = 0;
		for (int i = 0; i < numberOfMesurments; i++)
		{
			//mesurmentsArrayResult.push_back(0);
			if (abs(mesurmentsArrayDeviations[i]) > KS)
			{
				mesurmentsArrayResult.push_back(0);				
			}
			else
			{
				mesurmentsArrayResult.push_back(mesurmentsArrayEnter[i]);
				numberOfMesurmentsResult++;
			}
		}
		this->numberOfMesurmentsResult = numberOfMesurmentsResult;
		this->mesurmentsArrayResult = mesurmentsArrayResult;
	}

	void set_mesurmentsResultResult() {

		double mesurmentsArrayResultSumm = 0;
		double mesurmentsResultResult=0;

		for (int i = 0; i < numberOfMesurments; i++)
		{
			mesurmentsArrayResultSumm = mesurmentsArrayResultSumm + mesurmentsArrayResult[i];
		}
		mesurmentsResultResult = mesurmentsArrayResultSumm / numberOfMesurmentsResult;

		this->mesurmentsResultResult = mesurmentsResultResult;
	}

	void print() {
		cout << "Results in tabular form" << endl;
		cout << "Program changes Gross errors to 0" << endl;
		cout << endl;

		cout << " i  | Array | Deviations | DeviationsSquared | Result" << endl;
		cout << "--------------------------------------------------------------------------------------" << endl;

		for (int i = 0; i < numberOfMesurments; i++)
		{
			cout << i + 1<<"   |   " <<mesurmentsArrayEnter[i]<< "   |   " << mesurmentsArrayDeviations[i] << "   |   " << mesurmentsArrayDeviationsSquared[i] << "   |   " << mesurmentsArrayResult[i] << endl; //, i + 1, Math.Round(measurmentsArray[i], 6), Math.Round(measurmentsArrayRomanovskyBeta[i], 6), Math.Round(measurmentsArrayRomanovskyResult[i], 6));
			cout << "--------------------------------------------------------------------------------------" << endl;
		}
		cout << "Average enter element= " << mesurmentsArrayEnterAverage << endl;
		cout << "criterionCharlier= " << criterionCharlier << endl;
		cout << "KS= " << KS << endl;
		cout << "Average element after gross errors deleted= " << mesurmentsResultResult << endl;
	}

	void printToFile(string fileNamePart) {
		stringstream ss;
		ss << fileNamePart << ".txt";
		string fileName = ss.str();
		fstream f;
		f.open(fileName, ios::out);
		f << "Results in tabular form" << endl;
		f << "Program changes Gross errors to 0" << endl;
		f << endl;

		f << " i  | Array | Deviations | DeviationsSquared | Result" << endl;
		f << "--------------------------------------------------------------------------------------" << endl;

		for (int i = 0; i < numberOfMesurments; i++)
		{
			f << i + 1 << "   |   " << mesurmentsArrayEnter[i] << "   |   " << mesurmentsArrayDeviations[i] << "   |   " << mesurmentsArrayDeviationsSquared[i] << "   |   " << mesurmentsArrayResult[i] << endl; //, i + 1, Math.Round(measurmentsArray[i], 6), Math.Round(measurmentsArrayRomanovskyBeta[i], 6), Math.Round(measurmentsArrayRomanovskyResult[i], 6));
			f << "--------------------------------------------------------------------------------------" << endl;
		}
		f << "Average enter element= " << mesurmentsArrayEnterAverage << endl;
		f << "criterionCharlier= " << criterionCharlier << endl;
		f << "KS= " << KS << endl;
		f << "Average element after gross errors deleted= " << mesurmentsResultResult << endl;
		f.close();
	}

private:
	double criterionCharlier;
	double KS;
};