 File("input2.txt");
        Scanner sc;
        try {
            sc = new Scanner(myObj);
            solve(sc);
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }