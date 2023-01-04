# 3. OOP
## Materials
- [OOP](https://docs.oracle.com/javase/tutorial/java/concepts/index.html)
- [Lecture 3](https://drive.google.com/file/d/17R4SCXEd9B8a89UsJ-nLNYMFwM\_p8Fn9/view?usp=sharing)
- [Lecture 4](https://drive.google.com/file/d/14RC5dJno\_FcSfBe\_FAd9zeqpFSUj8odH/view?usp=sharing)
- [Reflection](https://docs.oracle.com/javase/tutorial/reflect/)
- [Reflections Lib](https://github.com/ronmamo/reflections)
- [Faker](https://github.com/DiUS/java-faker)
## VideoLectures
-  [03.oop.u1. Classes & Object](https://youtu.be/e8CFYiHeUi0)
-  [03.oop.u2. Reflections. ENUM](https://youtu.be/RrqTz-Qbtqg)
-  [03.oop.u3. OOP principles](https://youtu.be/zG-MNwEW6JY)
-  [03.oop.u4. Equals&HashCodes. Abstract Classes](https://youtu.be/zPmcv6xRoag)
-  [03.oop.u5. Wrapper Classes](https://youtu.be/W5uTGYVeslk)
-  [03.oop.u6. Interfaces](https://youtu.be/JGgKbMcDnOo)
## Task #3
Before start creating source code, read carefully all materials about OOP. It is not only 3 principles for interview;)
Store functionality should be based on above principles.
Classes to create:
- `Product` with such attributes as [name, rate, price]
- `Category` classes with the `name` attribute, for each store category [bike, phone, milk] and `products list`
- `Store` - class that should handle `category list`
- `RandomStorePopulator` - utility class that will populate out store/category with fake data using `Faker` lib
- `StoreApp` - class with main method to execute our store scenario.
When invoke main method, application should init store with categories and products and `pretty` print this data.
Also, categories should be read dynamically (at runtime), from base category package using `reflections` lib.

++Read this file when you have difficulty, or even before.
## Hints and FAQs
### Where to put classes?
Classes have to be put in relevant modules:
- `StoreApp` → `consoleApp`. `consoleApp` module contains only class `StoreApp` with `main()` method, which `creates` instance of `Store`, fill created store with `Products` and prints all the categories and all the products. The main purpose of this module is to run the store.
- `Product`, `Category`, `BikeCategory`, `PhoneCategory`, `MilkCategory` → `domain`. `domain` module contains only domain classes. The content of this module answers the question: "What is contained in the store?" In addition to products and product categories, in the future we could put such domain classes as `User`, `Admin`, `Cart` and so on here.
- `Store`, `RandomStorePopulator` → `store`. `store` module contains all the business logic. The content of this module answers the question: "How is the business logic implemented?" In the future, there may be interfaces here. Database connection logic and so on. Perhaps now it makes sense to think about creating auxiliary classes `StoreHelper`, into which we will transfer part of the business logic from `Store`. In order our `Store` class to comply with the `SingleResponsibility` principle and not contain many methods loosely related to each other.
### Packages
The names of packages in modules can be as follows:
`by.issoft.consoleApp`, `by.issoft.domain`, `by.issoft.store`. Further inside each of the modules, we can choose the grouping that is convenient for us. For example, child categories can be grouped in the `by.issoft.domain.categories` package. Be creative and use common sense.
### Code in small chunks
This task has three sub-tasks:
- creation of domain classes **(20%)**;
- connection of `Faker` and generation of random product names, prices and rates **(30%)**;
- use of `reflections` in order to create instances of child categories and add them into category list. Remember! You should not create categories by “hands” with help of constructors. All child categories have to be created with help of reflections **(50%)**!
### How then to work smoother and faster?
First of all, create a new branch in your `GitHub` project called `03OOP`. And after creating this branch, create a branch from it to perform the simplest task, for example, with the name `03OOP-domain`. In this branch, create domain classes and create a `pull request` to merge with the `03OOP` branch. After completing this part of the task, when I do a `merge`, create a new branch, for example `03OOP-populator`, in which you work with `Faker` and `RandomStorePopulator`. Further, by analogy, we act with `reflections`.
Why is this course of action preferable? When you turn in the whole assignment at once, I have to look at the entire code at once. Usually it is 10-15 files. Often I find serious errors that lead to the fact that the code has to be rewritten. In addition, it is quite difficult to consider all the changes in one sitting. It takes me about an hour to do a good `Code Revew` and also write comments. To avoid this, we write the code in small portions. This way we will not make big mistakes, we will save our time, and on `Code Review` each of the subtasks will take me only 10-20 minutes. This way you will receive feedback from me the same or the next day after you make a `pull request`.
