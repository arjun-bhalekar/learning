import "./Expenses.css";
import ExpenseItem from "./ExpenseItem";
import Card from "../UI/Card";
import React, { useState } from "react";
import ExpensesFilter from "./ExpensesFilter";

const Expenses = (props) => {
 // const items = props.items;

  const [filteredYear, setFilteredYear] =  useState('2020');

  const filterChangeHandler = (selectedYear) => {
    console.log('selected Year :'+selectedYear);
    setFilteredYear(selectedYear);
  };

  const filteredExpenses = props.items.filter(expense => {
    if(filteredYear==='all')
      return true;
    else
      return expense.date.getFullYear().toString()===filteredYear;
  });

  let expenseContent = <p>No expenses found.</p>;
   
  if(filteredExpenses.length > 0){
    expenseContent = filteredExpenses.map((expense) => (
      <ExpenseItem
        key = {expense.id}
        title={expense.title}
        amount={expense.amount}
        date={expense.date}
      />
    ));
  }

  
  return (
    <div>
      <Card className="expenses">
        <ExpensesFilter
          onFilterChange={filterChangeHandler}
          selected={filteredYear}
        />

        {expenseContent}

        
      </Card>
    </div>
  );
};

export default Expenses;
