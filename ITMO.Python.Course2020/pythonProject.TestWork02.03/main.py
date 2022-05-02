from builtins import print
from math import sqrt
from abc import ABC, abstractmethod
import time
import itertools
from random import shuffle


# Задание 2.3
# Декоратор и метакласс

###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс

def get_first_name(self):
    return self.first_name


def get_last_name(self):
    return self.last_name


def get_middle_name(self):
    return self.middle_name


def init(self, first_name, last_name, middle_name):
    self.first_name = first_name
    self.last_name = last_name
    self.middle_name = middle_name


class BaseUser(object):
    def __str__(self):
        return '<user-object/>'


attrs = {
    'first_name': '',
    'last_name': '',
    'middle_name': '',
    'get_first_name': get_first_name,
    'get_last_name': get_last_name,
    'get_middle_name': get_middle_name,
    '__init__': init
}

bases = (
    BaseUser,
)


###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс###Метакласс

# Декоратор
def method_friendly_decorator(method_to_decorate):
    def wrapper(self):
        print("Run method: " + str(method_to_decorate.__name__))
        return method_to_decorate(self)

    return wrapper


# Класс миксин, проверяем из какого именно класса-миксина будет наследоваться метод
class Mixin1(object):
    def whereDevelope(self):
        print("The program was developed in the glorious city of Kudrovo!")


# Класс миксин, проверяем из какого именно класса-миксина будет наследоваться метод
class Mixin2(object):
    def whereDevelope(self):
        print("The program was developed in the glorious city of St. Petersburg")


class AuthorInformation:
    def __init__(self, inf):
        self.__inf = inf

    def getInf(self):
        return "Author:" + str(self.__inf)

    def printInf(self):
        print(self.__inf)


class mesurmentsResult(ABC):
    __numberOfMesurments = 1
    __mesurmentsArrayEnter = []
    __mesurmentsArrayEnterAverage = 1
    __mesurmentsArrayDeviations = []
    __mesurmentsArrayDeviationsSquared = []
    __mesurmentsArrayDeviationsSquaredSumm = 1
    __standardDeviation = 1
    __mesurmentsArrayResult = []
    __mesurmentsResultResult = 1
    __numberOfMesurmentsResult = 1
    __inf = ""

    @abstractmethod
    def set_mesurmentsArrayResult(self):
        pass

    @abstractmethod
    def set_mesurmentsResultResult(self):
        pass

    @abstractmethod
    def printClass(self):
        pass

    @abstractmethod
    def printClassToFile(self, dirfile, namefile):
        pass

    def printAuthorInformation(self):
        print(self.__AuthorInformation.getInf())

    def __init__(self, measurementsNumber, measurementsArray, inf):
        self.__numberOfMesurments = measurementsNumber
        self.__mesurmentsArrayEnter = measurementsArray
        self.__inf = inf
        self.__AuthorInformation = AuthorInformation(inf)

    def getNumberOfMesurments(self):
        return self.__numberOfMesurments

    def getMesurmentsArrayEnter(self):
        return self.__mesurmentsArrayEnter

    def getMesurmentsArrayEnterAverage(self):
        return self.__mesurmentsArrayEnterAverage

    def getMesurmentsArrayDeviations(self):
        return self.__mesurmentsArrayDeviations

    def getMesurmentsArrayDeviationsSquared(self):
        return self.__mesurmentsArrayDeviationsSquared

    def getMesurmentsArrayDeviationsSquaredSumm(self):
        return self.__mesurmentsArrayDeviationsSquaredSumm

    def getStandardDeviation(self):
        return self.__standardDeviation

    def getMesurmentsArrayResult(self):
        return self.__mesurmentsArrayResult

    def getMesurmentsResultResult(self):
        return self.__mesurmentsResultResult

    def getNumberOfMesurmentsResult(self):
        return self.__numberOfMesurmentsResult

    def getInf(self):
        return self.__inf

    def set_mesurmentsArrayEnterAverage(self):
        mesurmentsArrayEnterSumm = 0
        for i in self.getMesurmentsArrayEnter():
            mesurmentsArrayEnterSumm = mesurmentsArrayEnterSumm + i
        mesurmentsArrayEnterAverage = mesurmentsArrayEnterSumm / self.getNumberOfMesurments()
        self.__mesurmentsArrayEnterAverage = mesurmentsArrayEnterAverage

    def set_mesurmentsArrayDeviations(self):
        mesurmentsArrayDeviations = []
        for i in self.getMesurmentsArrayEnter():
            mesurmentsArrayDeviations.append(i - self.getMesurmentsArrayEnterAverage())
        self.__mesurmentsArrayDeviations = mesurmentsArrayDeviations

    def set_mesurmentsArrayDeviationsSquared(self):
        mesurmentsArrayDeviationsSquared = []
        for i in self.getMesurmentsArrayDeviations():
            mesurmentsArrayDeviationsSquared.append(i * i)
        self.__mesurmentsArrayDeviationsSquared = mesurmentsArrayDeviationsSquared

    def set_mesurmentsArrayDeviationsSquaredSumm(self):
        mesurmentsArrayDeviationsSquaredSumm = 0
        for i in self.getMesurmentsArrayDeviationsSquared():
            mesurmentsArrayDeviationsSquaredSumm = mesurmentsArrayDeviationsSquaredSumm + i
        self.__mesurmentsArrayDeviationsSquaredSumm = mesurmentsArrayDeviationsSquaredSumm

    def set_standardDeviation(self):

        standardDeviation = sqrt(self.getMesurmentsArrayDeviationsSquaredSumm() / (self.getNumberOfMesurments() - 1))
        self.__standardDeviation = standardDeviation

    def set_mesurmentsArrayResult(self, mesurmentsArrayResult):
        self.__mesurmentsArrayResult = mesurmentsArrayResult

    def set_mesurmentsResultResult(self, mesurmentsResultResult):
        self.__mesurmentsResultResult = mesurmentsResultResult

    def set_numberOfMesurmentsResult(self, numberOfMesurmentsResult):
        self.__numberOfMesurmentsResult = numberOfMesurmentsResult

    def makeBeatifulMesurmentsResult(self):
        self.set_mesurmentsArrayEnterAverage()
        self.set_mesurmentsArrayDeviations()
        self.set_mesurmentsArrayDeviationsSquared()
        self.set_mesurmentsArrayDeviationsSquaredSumm()
        self.set_standardDeviation()
        # print("красиво сделано")
        # print("self.getMesurmentsArrayEnter()", self.getMesurmentsArrayEnter())
        # print("self.getMesurmentsArrayEnterAverage()", self.getMesurmentsArrayEnterAverage())
        # print("self.getMesurmentsArrayDeviations()", self.getMesurmentsArrayDeviations())
        # print("self.getMesurmentsArrayDeviationsSquared()", self.getMesurmentsArrayDeviationsSquared())
        # print("self.getMesurmentsArrayDeviationsSquaredSumm()", self.getMesurmentsArrayDeviationsSquaredSumm())
        # print("self.getStandardDeviation()", self.getStandardDeviation())


################################################################################################################################################################################
class mesurmentsResultCharlier(mesurmentsResult, Mixin1, Mixin2):
    # больше 20
    __criterionCharlier = 0
    __KS = 0

    def getCriterionCharlier(self):
        return self.__criterionCharlier

    def getKS(self):
        return self.__KS

    def __init__(self, measurementsNumber, measurementsArray, inf):
        super().__init__(measurementsNumber, measurementsArray, inf)

    def __add__(self, other):
        if isinstance(other, self.__class__):
            if (self.getNumberOfMesurments() + other.getNumberOfMesurments() > 20):
                return mesurmentsResultCharlier(self.getNumberOfMesurments() + other.getNumberOfMesurments(),
                                                self.getMesurmentsArrayEnter() + other.getMesurmentsArrayEnter(),
                                                self.getInf())
            else:
                print("Воспользуйтесь критерием Романовского")
        else:
            raise TypeError("Не могу добавить {1} к {0}".
                            format(self.__class__, type(other)))

    def __subtract__(self, other):
        if isinstance(other, self.__class__):
            if (self.getNumberOfMesurments() - other.getNumberOfMesurments() > 1):
                if (self.getNumberOfMesurments() - other.getNumberOfMesurments() > 20):
                    return mesurmentsResultCharlier(self.getNumberOfMesurments() - other.getNumberOfMesurments(),
                                                    self.getMesurmentsArrayEnter() - other.getMesurmentsArrayEnter(),
                                                    self.getInf())
                else:
                    print("Воспользуйтесь критерием романовского")
            else:
                print("Не могу вычесть {1} из {0}".format(self.getNumberOfMesurments(), other.getNumberOfMesurments()))
        else:
            TypeError("Не могу вычесть {1} из {0}".
                      format(self.__class__, type(other)))

    def set_criterionCharlier(self):

        numberOfMesurments = super().getNumberOfMesurments()

        if numberOfMesurments <= 5:
            criterionCharlier = 1.3
        elif (5 < numberOfMesurments and numberOfMesurments < 10):
            criterionCharlier = (numberOfMesurments - 5) * (1.65 - 1.3) / (10 - 5)
        elif numberOfMesurments == 10:
            criterionCharlier = 1.65
        elif 10 < numberOfMesurments and numberOfMesurments < 20:
            criterionCharlier = (numberOfMesurments - 10) * (1.96 - 1.65) / (20 - 10)
        elif (numberOfMesurments == 20):
            criterionCharlier = 1.96  # 5
        elif (20 < numberOfMesurments and numberOfMesurments < 30):
            criterionCharlier = (numberOfMesurments - 20) * (2.13 - 1.96) / (30 - 20)
        elif (numberOfMesurments == 30):
            criterionCharlier = 2.13  # 7
        elif (30 < numberOfMesurments and numberOfMesurments < 40):
            criterionCharlier = (numberOfMesurments - 30) * (2.24 - 2.13) / (40 - 30)
        elif (numberOfMesurments == 40):
            criterionCharlier = 2.24  # 9
        elif (40 < numberOfMesurments and numberOfMesurments < 50):
            criterionCharlier = (numberOfMesurments - 40) * (2.32 - 2.24) / (50 - 40)
        elif (numberOfMesurments == 50):
            criterionCharlier = 2.32  # 11
        elif (50 < numberOfMesurments and numberOfMesurments < 100):
            criterionCharlier = (numberOfMesurments - 50) * (2.58 - 2.32) / (100 - 50)
        else:
            criterionCharlier = 2.58

        self.__criterionCharlier = criterionCharlier

    def set_KS(self):
        KS = self.getCriterionCharlier() * super().getStandardDeviation()
        self.__KS = KS

    def set_mesurmentsArrayResult(self):
        mesurmentsArrayResult = []
        numberOfMesurmentsResult = 0
        count = 0
        for i in self.getMesurmentsArrayDeviations():
            if (i > self.getKS()):
                mesurmentsArrayResult.append(0)
            else:

                mesurmentsArrayResult.append(self.getMesurmentsArrayEnter()[count])
                numberOfMesurmentsResult = numberOfMesurmentsResult + 1
            count = count + 1

        # self.__numberOfMesurmentsResult = numberOfMesurmentsResult
        # self.__mesurmentsArrayResult = mesurmentsArrayResult
        super().set_numberOfMesurmentsResult(numberOfMesurmentsResult)
        # numberOfMesurmentsResult = numberOfMesurmentsResult
        # self.__mesurmentsArrayResult = mesurmentsArrayResult
        super().set_mesurmentsArrayResult(mesurmentsArrayResult)

    def set_mesurmentsResultResult(self):

        mesurmentsArrayResultSumm = 0

        for i in self.getMesurmentsArrayResult():
            mesurmentsArrayResultSumm = mesurmentsArrayResultSumm + i

        mesurmentsResultResult = mesurmentsArrayResultSumm / self.getNumberOfMesurmentsResult()
        # self.__mesurmentsResultResult = mesurmentsResultResult
        super().set_mesurmentsResultResult(mesurmentsResultResult)

    def printClass(self):
        numberOfMesurments = self.getNumberOfMesurments()
        print("Charlier criterion")
        print()
        print("Program changes Gross errors to 0")
        print()

        print("i".ljust(10), "Array".ljust(20), "Deviations".ljust(20), "DeviationsSquared".ljust(20),
              "Result".ljust(20))
        print("----------------------------------------------------------------------------------")

        i = 0
        while i < numberOfMesurments:
            print(str(i + 1).ljust(10),
                  str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            print("----------------------------------------------------------------------------------")
            i = i + 1

        print("Average enter element= ", self.getMesurmentsArrayEnterAverage())
        print("criterionCharlier= ", self.getCriterionCharlier())
        print("KS= ", self.getKS())
        print("Average element after gross errors deleted= ", self.getMesurmentsResultResult())

    def printClassToFile(self, dirfile, namefile):

        # Работа с файлом(запись в файл):

        namefile = dirfile + namefile  # полный путь к файлу
        myfile = open(namefile, 'w')  # Открывает файл (создает/очищает)

        myfile.write("Charlier criterion")
        myfile.write('\n')
        myfile.write("Results in tabular form")
        myfile.write('\n')
        myfile.write("Program changes Gross errors to 0")
        myfile.write('\n')

        myfile.write("i".ljust(10) + "Array".ljust(20) + "Deviations".ljust(20) + "DeviationsSquared".ljust(20) +
                     "Result".ljust(20))
        myfile.write("----------------------------------------------------------------------------------")

        i = 0
        while i < self.getNumberOfMesurments():
            myfile.write(str(i + 1).ljust(10)
                         + str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20)
                         + str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20)
                         + str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20)
                         + str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            myfile.write('\n')
            myfile.write("----------------------------------------------------------------------------------")
            myfile.write('\n')
            i = i + 1

        myfile.write("Average enter element= " + str(self.getMesurmentsArrayEnterAverage()))
        myfile.write('\n')
        myfile.write("criterionCharlier= " + str(self.getCriterionCharlier()))
        myfile.write('\n')
        myfile.write("KS= " + str(self.getKS()))
        myfile.write('\n')
        myfile.write("Average element after gross errors deleted= " + str(self.getMesurmentsResultResult()))

        myfile.close()
        # def printClassToFile(self):

        # coding: utf-8
        # Ввод списка с клавиатуры:
        # list1 = list(map(int, input().split()))
        # Работа с файлом(запись в файл):
        # dirfile = "D:\\data\\"    # можете заменить на свой каталог
        # namefile = dirfile + "myfile1.txt"    # полный путь к файлу
        # myfile = open(namefile, 'w')     # Открывает файл (создает/очищает)
        # for it in list1:          # Запись всех строк из списка в файл
        #    myfile.write(str(it))
        #    myfile.write('\n')
        # myfile.close()

    @method_friendly_decorator
    def makeAllWork(self):
        self.makeBeatifulMesurmentsResult()
        self.set_criterionCharlier()
        self.set_KS()
        self.set_mesurmentsArrayResult()
        self.set_mesurmentsResultResult()
        self.printClass()


###############################################################################################################################################################################
class mesurmentsResultRomanovsky(mesurmentsResult, Mixin1, Mixin2):
    # меньше 20
    __criterionRomanovsky = 0
    __mesurmentsArrayRomanovskyBeta = []

    def getCriterionRomanovsky(self):
        return self.__criterionRomanovsky

    def getMesurmentsArrayRomanovskyBeta(self):
        return self.__mesurmentsArrayRomanovskyBeta

    def __init__(self, measurementsNumber, measurementsArray, inf):
        super().__init__(measurementsNumber, measurementsArray, inf)

    def __add__(self, other):
        if isinstance(other, self.__class__):
            if (self.getNumberOfMesurments() + other.getNumberOfMesurments() <= 20):
                return mesurmentsResultRomanovsky(self.getNumberOfMesurments() + other.getNumberOfMesurments(),
                                                  self.getMesurmentsArrayEnter() + other.getMesurmentsArrayEnter(),
                                                  self.getInf())
            else:
                print("Воспользуйтесь критерием Шарли")
        else:
            raise TypeError("Не могу добавить {1} к {0}".
                            format(self.__class__, type(other)))

    def __subtract__(self, other):
        if isinstance(other, self.__class__):
            if (self.getNumberOfMesurments() - other.getNumberOfMesurments() > 1):
                if (self.getNumberOfMesurments() - other.getNumberOfMesurments() <= 20):
                    return mesurmentsResultRomanovsky(self.getNumberOfMesurments() - other.getNumberOfMesurments(),
                                                      self.getMesurmentsArrayEnter() - other.getMesurmentsArrayEnter(),
                                                      self.getInf())
                else:
                    print("Воспользуйтесь критерием Шарли")
            else:
                print("Не могу вычесть {1} из {0}".format(self.getNumberOfMesurments(), other.getNumberOfMesurments()))
        else:
            TypeError("Не могу вычесть {1} из {0}".
                      format(self.__class__, type(other)))

    def set_criterionRomanovsky(self):
        numberOfMesurments = super().getNumberOfMesurments()

        print(
            "Type '1', if you want significance level to be 0.01. Type '2', if you want significance level to be 0.02.")
        print(
            "Type '3', if you want significance level to be 0.05. Type '4', if you want significance level to be 0.10.")
        caseSwitch = int(input("Your value:"))
        criterionRomanovsky = 1

        if caseSwitch == 1:

            if (numberOfMesurments == 4):
                criterionRomanovsky = 1.73
            elif (4 < numberOfMesurments and numberOfMesurments < 6):
                criterionRomanovsky = (numberOfMesurments - 4) * (2.16 - 1.73) / (6 - 4)
            elif (numberOfMesurments == 6):
                criterionRomanovsky = 2.16
            elif (6 < numberOfMesurments and numberOfMesurments < 8):
                criterionRomanovsky = (numberOfMesurments - 6) * (2.43 - 2.16) / (8 - 6)
            elif (numberOfMesurments == 8):
                criterionRomanovsky = 2.43  # 5
            elif (8 < numberOfMesurments and numberOfMesurments < 10):
                criterionRomanovsky = (numberOfMesurments - 8) * (2.62 - 2.43) / (10 - 8)
            elif (numberOfMesurments == 10):
                criterionRomanovsky = 2.62
            elif (10 < numberOfMesurments and numberOfMesurments < 12):
                criterionRomanovsky = (numberOfMesurments - 10) * (2.75 - 2.62) / (12 - 10)
            elif (numberOfMesurments == 12):
                criterionRomanovsky = 2.75
            elif (12 < numberOfMesurments and numberOfMesurments < 15):
                criterionRomanovsky = (numberOfMesurments - 12) * (2.90 - 2.75) / (15 - 12)
            elif (numberOfMesurments == 15):
                criterionRomanovsky = 2.90  # 11
            elif (15 < numberOfMesurments and numberOfMesurments < 20):
                criterionRomanovsky = (numberOfMesurments - 15) * (3.08 - 2.90) / (20 - 15)
            else:
                criterionRomanovsky = 3.08


        elif caseSwitch == 2:

            if (numberOfMesurments == 4):
                criterionRomanovsky = 1.72  # 1
            elif (4 < numberOfMesurments and numberOfMesurments < 6):
                criterionRomanovsky = (numberOfMesurments - 4) * (2.13 - 1.72) / (6 - 4)
            elif (numberOfMesurments == 6):
                criterionRomanovsky = 2.13
            elif (6 < numberOfMesurments and numberOfMesurments < 8):
                criterionRomanovsky = (numberOfMesurments - 6) * (2.37 - 2.13) / (8 - 6)
            elif (numberOfMesurments == 8):
                criterionRomanovsky = 2.37
            elif (8 < numberOfMesurments and numberOfMesurments < 10):
                criterionRomanovsky = (numberOfMesurments - 8) * (2.54 - 2.37) / (10 - 8)
            elif (numberOfMesurments == 10):
                criterionRomanovsky = 2.54
            elif (10 < numberOfMesurments and numberOfMesurments < 12):
                criterionRomanovsky = (numberOfMesurments - 10) * (2.66 - 2.54) / (12 - 10)
            elif (numberOfMesurments == 12):
                criterionRomanovsky = 2.66
            elif (12 < numberOfMesurments and numberOfMesurments < 15):
                criterionRomanovsky = (numberOfMesurments - 12) * (2.80 - 2.66) / (15 - 12)
            elif (numberOfMesurments == 15):
                criterionRomanovsky = 2.80
            elif (15 < numberOfMesurments and numberOfMesurments < 20):
                criterionRomanovsky = (numberOfMesurments - 15) * (2.96 - 2.80) / (20 - 15)
            else:
                criterionRomanovsky = 2.96

        elif caseSwitch == 3:

            if (numberOfMesurments == 4):
                criterionRomanovsky = 1.71
            elif (4 < numberOfMesurments and numberOfMesurments < 6):
                criterionRomanovsky = (numberOfMesurments - 4) * (2.10 - 1.71) / (6 - 4)
            elif (numberOfMesurments == 6):
                criterionRomanovsky = 2.10
            elif (6 < numberOfMesurments and numberOfMesurments < 8):
                criterionRomanovsky = (numberOfMesurments - 6) * (2.27 - 2.10) / (8 - 6)
            elif (numberOfMesurments == 8):
                criterionRomanovsky = 2.27
            elif (8 < numberOfMesurments and numberOfMesurments < 10):
                criterionRomanovsky = (numberOfMesurments - 8) * (2.41 - 2.27) / (10 - 8)
            elif (numberOfMesurments == 10):
                criterionRomanovsky = 2.41
            elif (10 < numberOfMesurments and numberOfMesurments < 12):
                criterionRomanovsky = (numberOfMesurments - 10) * (2.52 - 2.41) / (12 - 10)
            elif (numberOfMesurments == 12):
                criterionRomanovsky = 2.52
            elif (12 < numberOfMesurments and numberOfMesurments < 15):
                criterionRomanovsky = (numberOfMesurments - 12) * (2.64 - 2.52) / (15 - 12)
            elif (numberOfMesurments == 15):
                criterionRomanovsky = 2.64
            elif (15 < numberOfMesurments and numberOfMesurments < 20):
                criterionRomanovsky = (numberOfMesurments - 15) * (2.78 - 2.64) / (20 - 15)
            else:
                criterionRomanovsky = 2.78

        elif caseSwitch == 4:

            if numberOfMesurments == 4:
                criterionRomanovsky = 1.69  # 1
            elif 4 < numberOfMesurments and numberOfMesurments < 6:
                criterionRomanovsky = (numberOfMesurments - 4) * (2.00 - 1.69) / (6 - 4)
            elif numberOfMesurments == 6:
                criterionRomanovsky = 2.00
            elif 6 < numberOfMesurments and numberOfMesurments < 8:
                criterionRomanovsky = (numberOfMesurments - 6) * (2.17 - 2.00) / (8 - 6)
            elif numberOfMesurments == 8:
                criterionRomanovsky = 2.17
            elif 8 < numberOfMesurments and numberOfMesurments < 10:
                criterionRomanovsky = (numberOfMesurments - 8) * (2.29 - 2.17) / (10 - 8)
            elif numberOfMesurments == 10:
                criterionRomanovsky = 2.29
            elif 10 < numberOfMesurments and numberOfMesurments < 12:
                criterionRomanovsky = (numberOfMesurments - 10) * (2.39 - 2.29) / (12 - 10)
            elif numberOfMesurments == 12:
                criterionRomanovsky = 2.39
            elif 12 < numberOfMesurments and numberOfMesurments < 15:
                criterionRomanovsky = (numberOfMesurments - 12) * (2.49 - 2.39) / (15 - 12)
            elif numberOfMesurments == 15:
                criterionRomanovsky = 2.49
            elif 15 < numberOfMesurments and numberOfMesurments < 20:
                criterionRomanovsky = (numberOfMesurments - 15) * (2.62 - 2.49) / (20 - 15)
            else:
                criterionRomanovsky = 2.62

        else:
            print("Default case")

        self.__criterionRomanovsky = criterionRomanovsky
        # print("self.criterionRomanovsky",self.criterionRomanovsky)

    def set_mesurmentsArrayRomanovskyBeta(self):
        mesurmentsArrayRomanovskyBeta = []
        mesurmentsArrayDeviations = self.getMesurmentsArrayDeviations()
        # print("mesurmentsArrayDeviations", mesurmentsArrayDeviations )
        for i in mesurmentsArrayDeviations:
            mesurmentsArrayRomanovskyBeta.append(abs(i / self.getStandardDeviation()))
            # print(abs(i / super().getStandardDeviation()))
        # print("бета",mesurmentsArrayRomanovskyBeta)
        self.__mesurmentsArrayRomanovskyBeta = mesurmentsArrayRomanovskyBeta
        # print("self.mesurmentsArrayRomanovskyBeta",self.mesurmentsArrayRomanovskyBeta)

    def set_mesurmentsArrayResult(self):
        mesurmentsArrayResult = []
        numberOfMesurmentsResult = 0
        count = 0
        # print("self.mesurmentsArrayDeviations",self.mesurmentsArrayDeviations)
        # print("self.criterionRomanovsky", self.getCriterionRomanovsky())
        # print("self.getMesurmentsArrayRomanovskyBeta()", self.getMesurmentsArrayRomanovskyBeta())
        MesurmentsArrayRomanovskyBeta = self.getMesurmentsArrayRomanovskyBeta()
        CriterionRomanovsky = self.getCriterionRomanovsky()
        for i in MesurmentsArrayRomanovskyBeta:
            # print("i",i, CriterionRomanovsky)
            if (int(i) > int(CriterionRomanovsky)):
                mesurmentsArrayResult.append(0)
            else:
                mesurmentsArrayResult.append(self.getMesurmentsArrayEnter()[count])
                numberOfMesurmentsResult = numberOfMesurmentsResult + 1
            count = count + 1

        # self.__numberOfMesurmentsResult = numberOfMesurmentsResult
        super().set_numberOfMesurmentsResult(numberOfMesurmentsResult)
        # numberOfMesurmentsResult = numberOfMesurmentsResult
        # self.__mesurmentsArrayResult = mesurmentsArrayResult
        super().set_mesurmentsArrayResult(mesurmentsArrayResult)
        # print(numberOfMesurmentsResult)
        # print(mesurmentsArrayResult)
        # print("self.getNumberOfMesurmentsResult()", self.getNumberOfMesurmentsResult())
        # print("self.getMesurmentsArrayResult()", self.getMesurmentsArrayResult())

    def set_mesurmentsResultResult(self, **kwargs):

        mesurmentsArrayResultSumm = 0

        for i in self.getMesurmentsArrayResult():
            mesurmentsArrayResultSumm = mesurmentsArrayResultSumm + i

        mesurmentsResultResult = mesurmentsArrayResultSumm / self.getNumberOfMesurmentsResult()

        # print("mesurmentsArrayResultSumm", mesurmentsArrayResultSumm)
        # print("self.numberOfMesurmentsResult", self.numberOfMesurmentsResult)

        # self.__mesurmentsResultResult = mesurmentsResultResult
        super().set_mesurmentsResultResult(mesurmentsResultResult)

    def printClass(self):
        numberOfMesurments = super().getNumberOfMesurments()
        print("Results in tabular form")
        print("Program changes Gross errors to 0")
        print()

        print("i".ljust(10), "Array".ljust(20), "Deviations".ljust(20), "DeviationsSquared".ljust(20), "Beta".ljust(20),
              "Result".ljust(20))
        print(
            "---------------------------------------------------------------------------------------------------------------")

        i = 0
        while i < numberOfMesurments:
            print(str(i + 1).ljust(10),
                  str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayRomanovskyBeta()[i], 3)).ljust(20),
                  str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            print(
                "---------------------------------------------------------------------------------------------------------------")
            i = i + 1

        print("Average enter element= ", self.getMesurmentsArrayEnterAverage())
        print("criterionRomanovsky= ", self.getCriterionRomanovsky())
        print("Average element after gross errors deleted= ", self.getMesurmentsResultResult())

    def printClassToFile(self, dirfile, namefile):

        # Работа с файлом(запись в файл):

        namefile = dirfile + namefile  # полный путь к файлу
        myfile = open(namefile, 'w')  # Открывает файл (создает/очищает)

        myfile.write("Romanovsky criterion")
        myfile.write('\n')
        myfile.write("Results in tabular form")
        myfile.write('\n')
        myfile.write("Program changes Gross errors to 0")
        myfile.write('\n')
        myfile.write("i".ljust(10) + "Array".ljust(20) + "Deviations".ljust(20) +
                     "DeviationsSquared".ljust(20) + "Beta".ljust(20) + "Result".ljust(20))
        myfile.write('\n')

        myfile.write(
            "---------------------------------------------------------------------------------------------------------------")
        myfile.write('\n')
        i = 0

        while i < self.getNumberOfMesurments():
            myfile.write(str(i + 1).ljust(10) +
                         str(round(self.getMesurmentsArrayEnter()[i], 3)).ljust(20) +
                         str(round(self.getMesurmentsArrayDeviations()[i], 3)).ljust(20) +
                         str(round(self.getMesurmentsArrayDeviationsSquared()[i], 3)).ljust(20) +
                         str(round(self.getMesurmentsArrayRomanovskyBeta()[i], 3)).ljust(20) +
                         str(round(self.getMesurmentsArrayResult()[i], 3)).ljust(20))
            myfile.write('\n')
            myfile.write(
                "---------------------------------------------------------------------------------------------------------------")
            myfile.write('\n')
            i = i + 1

        myfile.write('\n')
        myfile.write("Average enter element= " + str(self.getMesurmentsArrayEnterAverage()))
        myfile.write('\n')
        myfile.write("criterionRomanovsky= " + str(self.getCriterionRomanovsky()))
        myfile.write('\n')
        myfile.write("Average element after gross errors deleted= " + str(self.getMesurmentsResultResult()))
        myfile.write('\n')

        myfile.close()

    @method_friendly_decorator
    def makeAllWork(self):
        self.makeBeatifulMesurmentsResult()
        self.set_criterionRomanovsky()
        self.set_mesurmentsArrayRomanovskyBeta()
        self.set_mesurmentsArrayResult()
        self.set_mesurmentsResultResult()
        self.printClass()


#################################################################################################################################################################################

User = type('User', bases, attrs)

first_name = (input('Enter your name:'))
last_name = (input('Enter your last name:'))
middle_name = (input('Enter your middle name:'))

user1 = User(first_name, last_name, middle_name)

#print(str(user1))
#print(user1.get_first_name())
#print(user1.get_last_name())
#print(user1.get_middle_name())

# Печать вступительной информации:
print("Metrology. Gross errors.")
print()
print("A gross error, or a miss, is the error of the result of an individual measurement")
print("included in a series of measurements, which for these conditions differs sharply")
print("from the rest of the results of this series.For single measurements, detect a miss")
print("impossible.With multiple measurements to detect misses use statistical criterion:")
print("Romanovsky, Charlier.")
print()
print("This program demonstrates work of two criterias: Romanovsky criteria, Charlier criteria.")
print()
print("Romanovsky criterion is applied if the number of measurements is less than 20.")
print("Charlier criterion is applied if the number of measurements is more than 20.")
print()

# measurementsArray1 = [11, 11.5, 11, 11, 11, 11, 11, 11, 11, 11]
# measurementsNumber1 =len(measurementsArray1)
# measurementsArray2 = [11, 11, 11, 11.6, 11, 11, 11]
# measurementsNumber2 = len(measurementsArray2)
# inf = "Bubyrev Dmitriy"
# print("Romanovsky criterion")
# mesurmentsArrayClass01 = mesurmentsResultRomanovsky(measurementsNumber1, measurementsArray1, inf)
# mesurmentsArrayClass02 = mesurmentsResultRomanovsky(measurementsNumber2, measurementsArray2, inf)
# mesurmentsArrayClass03 = mesurmentsArrayClass01.__add__(mesurmentsArrayClass02)

# mesurmentsArrayClass03.makeBeatifulMesurmentsResult()

# mesurmentsArrayClass03.set_criterionRomanovsky()
# mesurmentsArrayClass03.set_mesurmentsArrayRomanovskyBeta()
# mesurmentsArrayClass03.set_mesurmentsArrayResult()
# mesurmentsArrayClass03.set_mesurmentsResultResult()
# mesurmentsArrayClass03.printClass()


measurementsArray = []
y = 1
measurementsNumber = int(input('сколько элементом нужно в спике:'))
while y <= measurementsNumber:
    print("[Measure №", y, "]=", end="")
    measurementsArray.append(float(input()))
    y += 1
# конец цикла
print()
print(measurementsArray)
inf = user1.get_first_name() +" " + user1.get_last_name() + " " + user1.get_middle_name()
#
if (measurementsNumber <= 20):
    print("Romanovsky criterion")
    mesurmentsArrayClass01 = mesurmentsResultRomanovsky(measurementsNumber, measurementsArray, inf)

    # mesurmentsArrayClass01.makeBeatifulMesurmentsResult()
    # mesurmentsArrayClass01.set_criterionRomanovsky()
    # mesurmentsArrayClass01.set_mesurmentsArrayRomanovskyBeta()
    # mesurmentsArrayClass01.set_mesurmentsArrayResult()
    # mesurmentsArrayClass01.set_mesurmentsResultResult()
    # mesurmentsArrayClass01.printClass()

    mesurmentsArrayClass01.makeAllWork()

    dirfile = "D:\\data\\"  # можете заменить на свой каталог
    namefile = "myfileByburevRomanovskyCriterion.txt"  # полный путь к файлу

    mesurmentsArrayClass01.printClassToFile(dirfile, namefile)

    mesurmentsArrayClass01.printAuthorInformation()

    mesurmentsArrayClass01.whereDevelope()

else:
    print("Charlier criterion")
    mesurmentsArrayClass01 = mesurmentsResultCharlier(measurementsNumber, measurementsArray, inf)

    # mesurmentsArrayClass01.makeBeatifulMesurmentsResult()
    # mesurmentsArrayClass01.set_criterionCharlier()
    # mesurmentsArrayClass01.set_KS()
    # mesurmentsArrayClass01.set_mesurmentsArrayResult()
    # mesurmentsArrayClass01.set_mesurmentsResultResult()
    # mesurmentsArrayClass01.printClass()

    mesurmentsArrayClass01.makeAllWork()

    dirfile = "D:\\data\\"  # можете заменить на свой каталог
    namefile = "myfileByburevCharlierCriterion.txt"  # полный путь к файлу
    mesurmentsArrayClass01.printClassToFile(dirfile, namefile)

    mesurmentsArrayClass01.printAuthorInformation()

    mesurmentsArrayClass01.whereDevelope()
# конец ветвления
