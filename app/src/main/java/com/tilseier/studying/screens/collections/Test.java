package com.tilseier.studying.screens.collections;

public class Test {
    private int f1 = -1;
    private int f2 = -1;

    public Test (final int f1, final int f2)
    {
        this.f1 = f1;
        this.f2 = f2;
    }
    public int getF1()
    {
        return this.f1;
    }
    public int getF2()
    {
        return this.f2;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + f1;
        result = prime * result + f2;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Test other = (Test) obj;
        if (f1 != other.f1)
            return false;
        if (f2 != other.f2)
            return false;
        return true;
    }

}
