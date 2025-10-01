package com.example.designpatternsdemo.structuraldesgnpattern.facadepattern

/**
 * https://www.geeksforgeeks.org/difference-between-the-facade-proxy-adapter-and-decorator-design-patterns/?ref=asr14
 * When to use Facade: To hide system complexity from the client.
 *It provides a simplified interface to a complex system, making it easier to use.
 */

//Complex subsystem or some 3rd part library or data storage
class CollegeLibrary {
    //It's not a book library. It act as a 3rd party library.
    // Which supports all required functionality in college db.
    class DepartmentDao {
        fun insertDepartment() {
            println("Department details are inserted")
        }

        fun getDepartment() {
            println("Department details are fetched")
        }

        fun updateDepartment() {
            println("Department details are updated")
        }

        fun deleteDepartment() {
            println("Department details are deleted")
        }
    }
    //CRUD
    class EmployeeDao {
        fun insertEmployee() {
            println("Employee details are inserted")
        }

        fun getEmployee() {
            println("Employee details are fetched")
        }

        fun updateEmployee() {
            println("Employee details are updated")
        }

        fun deleteEmployee() {
            println("Employee details are deleted")
        }
    }

    //It might have #n of different methods.
}

/**
 * A facade class that serves as the middle layer between the client and the subsystems.
 * This class should offer simplified methods that wrap the interactions with the subsystems.
 *
 * Benefits: Expose only what is needed to client. Hide everything else.
 * Admin has more permission then a student, so write appropriate facade for each client.
 * A facade can use another facade.
 */
class EmployeeFacade {
    private val employeeDao = CollegeLibrary.EmployeeDao()
    fun insertEmployee() {
        /**
         *  Inside the facade methods, delegate the requests to the appropriate subsystem classes
         *  to perform the actual operations.
         */
        employeeDao.insertEmployee()
    }

    fun getEmployee() {
        //Tomorrow if return type is changes then just Facade need get updated.
        employeeDao.getEmployee()
    }

    fun updateEmployee() {
        employeeDao.updateEmployee()
    }

    fun deleteEmployee() {
        employeeDao.deleteEmployee()
    }
}

//Client
private fun main() {
    val employeeFacade = EmployeeFacade()
    employeeFacade.insertEmployee()
    employeeFacade.updateEmployee()
    employeeFacade.getEmployee()
    employeeFacade.deleteEmployee()
}

