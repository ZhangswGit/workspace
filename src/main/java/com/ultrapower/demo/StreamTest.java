package com.ultrapower.demo;

import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
    static class Book {
        private int id;

        private String name;//书名

        private double price;//价格

        private String type;//类型

        private LocalDate date;//出版时间

        public Book(int id, String name, double price, String type, LocalDate localDate) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.type = type;
            this.date = localDate;
        }

        @Override
        public String toString() {
            return this.getClass().getName() + "[" + this.getId() + "," + this.getName() + "," + this.getPrice() + "," + this.getType() + "," + this.getDate() + "]";
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public double getPrice() {
            return price;
        }

        public LocalDate getDate() {
            return date;
        }
    }

    private static final List<Book> BOOK_LIST = books();

    private static List<Book> books() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "tomcat", 50d, "服务器", LocalDate.parse("2014-05-17")));
        books.add(new Book(2, "nginx", 60d, "服务器", LocalDate.parse("2014-06-17")));
        books.add(new Book(3, "java", 70d, "编程语言", LocalDate.parse("2013-03-17")));
        books.add(new Book(4, "ruby", 50d, "编程语言", LocalDate.parse("2014-05-27")));
        books.add(new Book(5, "hadoop", 80d, "其它", LocalDate.parse("2015-11-17")));
        books.add(new Book(6, "spark", 45d, "其它", LocalDate.parse("2012-03-17")));
        books.add(new Book(7, "storm", 55d, "其它", LocalDate.parse("2014-10-17")));
        books.add(new Book(8, "kafka", 60d, "其它", LocalDate.parse("2016-03-14")));
        books.add(new Book(9, "lucene", 70d, "其它", LocalDate.parse("2012-05-17")));
        books.add(new Book(10, "solr", 35d, "其它", LocalDate.parse("2013-05-17")));
        books.add(new Book(11, "jetty", 90d, "服务器", LocalDate.parse("2014-05-17")));
        books.add(new Book(12, "设计模式", 60d, "其它", LocalDate.parse("2015-05-17")));
        books.add(new Book(13, "数据结构", 70d, "其它", LocalDate.parse("2011-05-17")));
        books.add(new Book(14, "敏捷开发", 50d, "其它", LocalDate.parse("2010-05-17")));
        books.add(new Book(15, "算法导论", 20d, "其它", LocalDate.parse("1999-05-17")));
        books.add(new Book(16, "算法导论", 90d, "其它", LocalDate.parse("2017-05-17")));
        return books;
    }

    @Test
    public void t1() {
        BOOK_LIST.stream().forEach(x -> System.out.println(x.getName()));
    }

    //ip地址中的参数转换为map index.do?itemId = 1 & userId = 19202 & token = 1111
    @Test
    public void t2() {
        String queryStr = "itemId = 1 & userId = 19202 & token = 1111";
        List<String[]> list = Arrays.asList(queryStr.split("&")).stream().map(x -> x.split("=")).collect(Collectors.toList());
        list.forEach(x -> {
            System.out.println(Arrays.toString(x));
        });
    }

    @Test
    public void t3() {
        String queryStr = "itemId = 1 & userId = 19202 & token = 1111";
        Map<String, String> map = Arrays.asList(queryStr.split("&")).stream().map(x -> x.split("=")).collect(Collectors.toMap(x -> x[0], x -> x[1]));
        System.out.println(map);
    }

    //非常常见
    @Test
    public void t4() {
        //把所有书的id取出来放到list中
        List<Integer> list = BOOK_LIST.stream().map(x -> x.getId()).collect(Collectors.toList());
        System.out.println(list);//[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]

        //转换成(1,2,3,4,5)这种形式  这种形式必须转为string
        String s = BOOK_LIST.stream().map(x -> x.getId() + "").collect(Collectors.joining(",", "(", ")"));
        System.out.println(s);
    }

    @Test
    public void t5() {
        //类型全部取出来并且去重 distinct或者set
        List<String> list = BOOK_LIST.stream().map(x -> x.getType()).distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void t6() {
        //价格由高到低排序
        List<Book> list = BOOK_LIST.stream().sorted((a, b) -> (int) (b.getPrice() - a.getPrice())).collect(Collectors.toList());
        list.forEach(x -> System.out.println(x.getPrice()));
        System.out.println("=========================");
        /**   LocalDate a = LocalDate.of(2012, 6, 30);源码这样解释的
         *   LocalDate b = LocalDate.of(2012, 7, 1);
         *   a.isAfter(b) == false
         *   a.isAfter(a) == false
         *   b.isAfter(a) == true*/
        //价格相同的由时间由近到远排
//      Compares its two arguments for order. Returns a negative integer, zero, or
//      a positive integer as the first argument is less than, equal to, or greater
//      than the second.   compare(a,b)方法  也就是说如果是-1就表示前面的比较小，他要排到前面
        Comparator<Book> comparator = (a, b) -> (int) (b.getPrice() - a.getPrice());
        /*
        可以这样来看：a.date在b.date后面的时候返回-1，说明此时a排在了b前面，再回来看
        a.date此时是after  b.date的，说明时间近的a排在了时间远的b前面  符合
         */
        List<Book> list1 = BOOK_LIST.stream().sorted(comparator.thenComparing((a, b) -> a.getDate().isAfter(b.getDate()) ? -1 : 1)).collect(Collectors.toList());
        list1.forEach(x -> {
            System.out.println(x.getPrice() + ":" + x.getDate());
        });

        System.out.println("=================================");
        //使用Comparator(comparing要求传入的是Function，代表被排序的类型和按照上面排序，但是它判断不出来传入的类型)
        List<Book> list2 = BOOK_LIST.stream().sorted(Comparator.comparing((Book b) -> b.getPrice()).reversed().thenComparing((Book b) -> b.getDate())).collect(Collectors.toList());
        list1.forEach(x -> {
            System.out.println(x.getPrice() + ":" + x.getDate());
        });

    }

    @Test
    public void t7() {
        //转为map,key为数的id
        Map<Integer, Double> map = BOOK_LIST.stream().collect(Collectors.toMap(x -> x.getId(), x -> x.getPrice()));
        System.out.println(map);
    }

    @Test
    public void t8() {
        //求平均价格
        OptionalDouble average = BOOK_LIST.stream().map(x -> x.getPrice()).mapToDouble(x -> x).average();
        if (average.isPresent()) System.out.println(average.orElseGet(() -> 0.0));

        Double price = BOOK_LIST.stream().collect(Collectors.averagingDouble(x -> x.getPrice()));
        System.out.println(price);
    }

    @Test
    public void t9() {
        //找最大最小
        Optional<Book> book = BOOK_LIST.stream().collect(Collectors.maxBy(Comparator.comparing((Book x) -> x.getPrice())));
        Optional<Book> book1 = BOOK_LIST.stream().collect(Collectors.minBy(Comparator.comparing((Book x) -> x.getPrice())));
        System.out.println(book.get().getPrice() + ":" + book1.get().getPrice());

        //时间最早的
        Optional<Book> book2 = BOOK_LIST.stream().collect(Collectors.maxBy(Comparator.comparing((Book x) -> x.getDate())));
        System.out.println(book2.get().getDate());

        //最贵的，时间是最早的
        Comparator<Book> comparator1 = Comparator.comparing((Book x) -> x.getPrice());
        Comparator<Book> comparator2 = Comparator.comparing((Book x) -> x.getDate()).reversed();
        Optional<Book> book3 = BOOK_LIST.stream().collect(Collectors.maxBy(comparator1.thenComparing(comparator2)));
        System.out.println(book3.get().getDate() + ":" + book3.get().getPrice());

    }

    @Test//分组
    public void t10() {
        //按照类别分组  对象方法的引用在这里使用到了
        Map<String, List<Book>> map = BOOK_LIST.stream().collect(Collectors.groupingBy(Book::getType));
        map.entrySet().forEach(x -> {
            System.out.println(x.getKey() + ":" + x.getValue());
        });
        //分组后计算数量   第二个参数也是传入一个Collectors
        Map<String, Long> map1 = BOOK_LIST.stream().collect(Collectors.groupingBy(x -> x.getType(), Collectors.counting()));
        map1.entrySet().forEach(x -> {
            System.out.println(x.getKey() + ":" + x.getValue());
        });

        //分组后计算价格  对象方法引用看来还是有用的
        Map<String, Double> map2 = BOOK_LIST.stream().collect(Collectors.groupingBy(Book::getType, Collectors.summingDouble(Book::getPrice)));
        map2.entrySet().forEach(x -> {
            System.out.println(x.getKey() + ":" + x.getValue());
        });

        //分组和计算平均
        Map<String, Double> map3 = BOOK_LIST.stream().collect(Collectors.groupingBy(Book::getType, Collectors.averagingDouble(Book::getPrice)));
        map2.entrySet().forEach(x -> {
            System.out.println(x.getKey() + ":" + x.getValue());
        });

        //分组后最贵的那本
        Map<String, Optional<Book>> map4 = BOOK_LIST.stream().collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(Comparator.comparing((Book x) -> x.getPrice()))));
        map4.entrySet().forEach(x -> {
            System.out.println(x.getKey() + ":" + x.getValue().get());
        });

        //分组后最晚出售的
        Map<String, Optional<Book>> map5 = BOOK_LIST.stream().collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(Comparator.comparing((Book x) -> x.getDate()))));
        map5.entrySet().forEach(x -> {
            System.out.println(x.getKey() + ":" + x.getValue().get());
        });

    }

    @Test//价格大于80，日期由近到远
    public void t11() {
        List<Book> list = BOOK_LIST.stream().filter(x -> x.getPrice() >= 80).sorted(Comparator.comparing((Book x) -> x.getDate()).reversed()).collect(Collectors.toList());
        list.forEach(System.out::println);
    }
}


