//
  This Question was asked in an amazon interview. :)
  
  Question: You are working for a telecommunications company that is trying to upgrade junction boxes all over Techlandia. Some of the junction boxes have already been upgraded, and other boxes have not. Your task is to identify the oldest boxes that need to be upgraded first but leaving the newer model boxes so that they will not be prioritized.
  All the junction boxes are identified by an alphanumeric identifier, followed by space delimited version information. The older generation uses space delimited lowercase English strings to identify the version, but the newer generation uses space delimited positive integers to identify the version. Your task is to sort the junction boxes in the following order:
  The older generation junction boxes with the earliest lexicographic version should come first.
  If there are any ties in the older generation, ties should be broken by the alphanumeric identifier.
  3. Finally, the newer generation boxes should also be retuned, in the original order, they were in the List
  Write a function or method to return the junction boxes in the specified order.
  Input: The input to the function/method consists of two arguments:
  numberOfBoxes, an integer representing the number of junction boxes.
  boxList, a list of Strings representing all of the identifiers and version information.
  Output: Return a list of strings representing the correctly ordered junction boxes.
//

//SOLUTION: 


using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace Question
{
    public class Program
    {
        public static void Main(string[] args)
        {
        //let boxList be the junction boxes
            var boxList = new string[6] {"r1 box ape bit","br8 eat nim did",
                    "w1 has uni gry","b4 xi me nu",
                    "t2 13 121 98","f3 52 54 31"};
            Queue<string> newVersionQueue = new Queue<string>();
            List<ObjectToSort> oldVersionQueue = new List<ObjectToSort>();
            
            //sorting old and new version queue
            foreach (var box in boxList) {
                var boxSplit = box.Split(' ')[1];
                if(! char.IsLetter(boxSplit[0])) {
                    newVersionQueue.Enqueue(box);    
                } else {
                    var ObjectToSort = new ObjectToSort(){
                        OriginalString = box,
                        firstObject = box.Substring(0, box.IndexOf(' ')),
                        secondObject = box.Substring(box.IndexOf(' ')+1), //this
                    };
                    oldVersionQueue.Add(ObjectToSort);
                }
            }
            var that = oldVersionQueue.OrderBy(f => f.secondObject).ThenBy(f => f.firstObject).Select(f=> f.OriginalString).ToList();
            while (newVersionQueue.Count > 0) {
                that.Add(newVersionQueue.Dequeue());
            }
            that.ForEach(Console.WriteLine);
            
        }
    }
    
    public class ObjectToSort {
        public String OriginalString {get;set;}
        public String firstObject {get;set;}
        public String secondObject {get;set;}
    }
}
