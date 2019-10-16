package com.pangpang6.books.guava.base;

import com.google.common.base.Equivalence;
import org.junit.Test;

/**
 * Created by jiangjiguang on 2017/12/12.
 */
public class EquivalenceTest {


    @Test
    public void identityTest(){
        Equivalence<Object> identity = Equivalence.identity();
        System.out.println("Identity Reflexive eq(null, null)="
                + identity.equivalent(null, null));
        System.out.println(null == null);

        Employee emp = new Employee("01", "Joe");
        System.out.println("Identity Reflexive eq(emp, emp)="
                + identity.equivalent(emp, emp));

        System.out.println(emp == emp);

        Employee sameEmp = new Employee("01", "Joe");
        System.out.println("Identity Reflexive eq(emp, sameEmp)="
                + identity.equivalent(emp, sameEmp));

        System.out.println(emp == sameEmp);


    }

    @Test
    public void equalsTest(){
        Employee emp = new Employee("01", "Joe");
        Employee sameEmp = new Employee("01", "Joe");

        Equivalence<Object> equals = Equivalence.equals();
        System.out.println("Equals Reflexive eq(emp, sameEmp)="
                + equals.equivalent(emp, sameEmp));
    }

    @Test
    public void equivalentTest(){
        Equivalence<Object> identity = Equivalence.identity();
        System.out.println("Identity Symmetric eq(2, 3)=eq(3, 2)? "
                + (identity.equivalent(2, 3) == identity.equivalent(3, 2)));

        System.out.println(identity.equivalent(2, 3));


        Employee emp1 = new Employee("01", "Joe");
        Employee emp2 = new Employee("02", "Sam");

        Equivalence<Object> equals = Equivalence.equals();
        System.out.println("Equals Symmetric eq(emp1, emp2)=eq(emp2, emp1)? "
                + (equals.equivalent(emp1, emp2) == equals.equivalent(emp2, emp1)));

        System.out.println(equals.equivalent(emp1, emp2));
    }


    public class Employee {
        private String id;
        private String name;

        Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return "Emp<" + id + "," + name + ">";
        }

        public boolean equals(Object o) {
            if (!(o instanceof Employee)) {
                return false;
            }
            Employee emp = (Employee) o;
            return emp.getId().equals(id) &&
                    emp.getName().equals(name);
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }

}
