# Sales-Taxation
Calculating sales tax, 2015

Problem

Sales tax is applicable at 10% on all goods, except books, food, and medical products which are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt that lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these inputs:

INPUT:
Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Theory
	The program is modelled along the lines of a Finite State Machine. 

	The Register can be in one of five states: INIT (program start), INPUT (Input 1:), ITEM (1 bottle...), EOF (Not used- marker for END of FILE), ERROR (if an error occurred). 
	The state machine transitions are self-explanatory. The output state is transit[input-state][token] where transit is a function defined in StateMachine.

Assumptions:
	1. Rounding can be done at the item level or at the total(aggregate level). 
