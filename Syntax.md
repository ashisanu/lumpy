# Introduction #

The syntax of Lumpy is a mixture of Python, Lua and Java. But it has its own "loop" syntax.


Lumpy is case insensitive. That means, it does not matter if you type "iF","IF" or "if".

Lumpy only knows "private" and "public" for information hinding within classes. "private" acts like "protected" in Java. "public" acts like in java.

Lumpy uses type inference. That means, you often (not always) need not to specify the datatype of a variable. Only in connection with templates,extern functions or operators you HAVE TO. Sometimes the compiler can't find the datatype of a class attribute.



All features are fully working:
# Details #


## if: ##
```
if expression
    code
end
elseif expression
    code
end
else
    code
end
```

## select: ##
```
select expression
    case expression
        code
    case expression
        code
    default
        code
end
```

## while loop: ##
```
loop expression
    code
end
```

## for loop: ##
```
loop var i = 0, i < 10, i = i +1
   code
end
//or:
loop var i = 0 to 10 [step 2]
   code
end
```

## foreach loop: ##
```
loop var i in collection
   code
end
```

## (local) variable: ##
```
var i:int //explicit datatype
var:int j, k, l //one datatype for all
var m = 10 //type inference
```

## function: ##
```
function func1(var1,var2,var3) //datatypes via typeinference
   var1 = "lol"
   var2 = 10.4
   var3 = var1

   return var1
end

//anonymous function:
var func = function:int(input:string)
    print input + "\n"
    return 20
end
func()

//overloading
function func1(var1:float,var2,var3)
   return func1(string(var1),var2,var3)
end
```

## class: ##
```
class testClass < baseClass //inherits all members and methods, no multiple inheritance
    private var attribute = 10 //attribute
    
    public function testMethod:void() //method
    end
    
    public function new(value) //constructor
        super.new(value)
    end
end
```

## property: ##
```
public property testVariable
   get
      return this._vari
   end
   set
      this._vari = value
   end
end
```

## indexer ##
```
public property fakeArray[index:int]
    get
        return this.array[index]
    end
    set
        this.array[index] = value
    end
end
```

## templates: ##
```
class<Datatype> container
   private var:Datatype value
   public function set(val:Datatype)
       this.value = val
   end
   public function get() //type inference
       return this.value
   end
   public function new() end
end
function program()
   var cont = new container<string>, cont2 = new container<int>
   cont.set "hello"
   cont2.set 42
   print cont.get()
   print cont2.get()
end
```

## generators: ##
```
class containerClass
    public generator:int(start:int)
        loop var i = start to start + 10
            yield return i
        end
        yield break //optional
    end
    public function new() end
end
function program()
    var gen = new containerClass()
    gen.start
    loop gen.hasnext()
        print gen.invoke(10)
    end
    //or:
    loop var i in gen
        print i
    end
end
```

## array ##
```
var array = new int[10][10]
array[2][3] = 100
print array[2][3]
```

## language ##
```
language c
   import "header.h"
end
```

## (cyclic) imports ##
```
//file a:
import b
//file b:
import a

import "file.h" //header files
import "file.o" //object files
```

## increment/decrement ##
There are no "++" or "--" operators. But there are "increment" and "decrement" statements.
```
var i = 0
increment i
decrement i
print i //0
```

## exception ##
```
try ex
    var test:object = null
    test.toString
    throw "lol"
    throw 42
end
catch NullPointerException
    print ex.toString()
end
catch string
    print ex
end
catch
    print "unknown"
end
```

## extern (interacting with libraries): ##
```
extern
    function func:void(lol1:float,lol:string) //no type inference
    struct test //no inheritance allowed
        var test:int, lol:float
        function method()
        end
    end
end
```


## operator overloading ##
Overloadable operators:
+,-,**,/,and,or,not,<,>,<=,>=,!=
```
operator <:boolean(left:String, Right:String)
    return left.length < right.length
end
function program()
    print "lol" < "rofl"
end
```**



## const ##
```
var const lol = 10
lol = 100 //error
```

## call by reference ##
```
function swap(ref left:int, ref right:int)
    var tmp = left
    left = right
    right = tmp
end
function program()
    var i = 10, j = 20
    swap i, j
    print i //20
    print j //10
end
```

## default parameter values ##
```
function default(var1:int = 100, var2:string = "Hello") end
function program()
    default
end
```

## abstract/final ##
abstract: It have to be extended.
final: It mustn't be extended.
```
class abstract base
	
	public function new()
	end
	
	public function abstract blub()
end

class extend < base
	public function blub()
		print "jahaaa"
	end
end
```



## slicing (Array) ##
```
var array = new int[20]
print array[..10] //creates a new array with first 10 elements
print array[10..] //creates a new array with 10 - 20
print array[4 .. 6] //elements from 4 to 6
print array[..-2] //last two elements
print array[..] //creates a new copy
```

## automatic array generation ##
```
var arr = [10,20,30,40,50]
var multiarr = [
   [10,20,30],
   [10,20,30],
   [10,20,30],
   [10,20,30]
]
```


## Static members (singleton) ##
```

class test
	public static var statisch:int = 99
	
	public static function test()
		print "ROFL"
	end
	
	public static property name
		get
			return "Peter"
		end
		set
			print "set name: " + value
		end
	end
	
	public static generator:int()
		var i = 2
		loop true
			i = i*2
			yield return i
		end
	end
end
```