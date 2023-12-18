//package day48;
//
//399. Evaluate Division
//Solved
//Medium
//Topics
//Companies
//Hint
//You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
//
//You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
//
//Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
//
//Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
//
// 
//
//Example 1:
//
//Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//Explanation: 
//Given: a / b = 2.0, b / c = 3.0
//queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
//return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
//note: x is undefined => -1.0
//Example 2:
//
//Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//Output: [3.75000,0.40000,5.00000,0.20000]
//Example 3:
//
//Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//Output: [0.50000,2.00000,-1.00000,-1.00000]
// 
//
//Constraints:
//
//1 <= equations.length <= 20
//equations[i].length == 2
//1 <= Ai.length, Bi.length <= 5
//values.length == equations.length
//0.0 < values[i] <= 20.0
//1 <= queries.length <= 20
//queries[i].length == 2
//1 <= Cj.length, Dj.length <= 5
//Ai, Bi, Cj, Dj consist of lower case English letters and digits
//
//class Solution {
//    public void dfs(String node, String dest, HashMap<String, HashMap<String, Double>> gr, HashSet<String> vis, double[] ans, double temp) {
//        if (vis.contains(node))
//            return;
//
//        vis.add(node);
//        if (node.equals(dest)) {
//            ans[0] = temp;
//            return;
//        }
//
//        for (Map.Entry<String, Double> entry : gr.get(node).entrySet()) {
//            String ne = entry.getKey();
//            double val = entry.getValue();
//            dfs(ne, dest, gr, vis, ans, temp * val);
//        }
//    }
//
//    public HashMap<String, HashMap<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
//        HashMap<String, HashMap<String, Double>> gr = new HashMap<>();
//
//        for (int i = 0; i < equations.size(); i++) {
//            String dividend = equations.get(i).get(0);
//            String divisor = equations.get(i).get(1);
//            double value = values[i];
//
//            gr.putIfAbsent(dividend, new HashMap<>());
//            gr.putIfAbsent(divisor, new HashMap<>());
//
//            gr.get(dividend).put(divisor, value);
//            gr.get(divisor).put(dividend, 1.0 / value);
//        }
//
//        return gr;
//    }
//
//    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
//        HashMap<String, HashMap<String, Double>> gr = buildGraph(equations, values);
//        double[] finalAns = new double[queries.size()];
//
//        for (int i = 0; i < queries.size(); i++) {
//            String dividend = queries.get(i).get(0);
//            String divisor = queries.get(i).get(1);
//
//            if (!gr.containsKey(dividend) || !gr.containsKey(divisor)) {
//                finalAns[i] = -1.0;
//            } else {
//                HashSet<String> vis = new HashSet<>();
//                double[] ans = {-1.0};
//                double temp = 1.0;
//                dfs(dividend, divisor, gr, vis, ans, temp);
//                finalAns[i] = ans[0];
//            }
//        }
//
//        return finalAns;
//    }
//}
