package org.example;

public class Test {
    private String name;
    private int weight;
    private int count;
    private String _package;

    public Test(String name, int weight, int count, String _package)
    {
        this.name = name;
        this.weight = weight;
        this.count = count;
        this._package = _package;
    }

    public Test()
    {}

    public String getName()
    {
        return name;
    }
    public int getWeight()
    {
        return weight;
    }
    public int getCount()
    {
        return count;
    }
    public String getPackage()
    {
        return _package;
    }
    public void setName(String name)
    {
        if(!name.isEmpty())
        {
            this.name = name;
        }
        else
        {
            System.out.println("Ошибка: пустая строка");
        }
    }
    public void setWeight(int weight)
    {
        if(weight > 0)
        {
            this.weight = weight;
        }
        else
        {
            System.out.println("Ошибка: число не больше нуля");
        }
    }
    public void setCount(int count)
    {
        if(count > 0)
        {
            this.count = count;
        }
        else
        {
            System.out.println("Ошибка: число не больше нуля");
        }
    }
}