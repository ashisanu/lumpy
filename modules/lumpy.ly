language c
	import "c/system/gc.o"
	import "c/system/string.o"
	import "c/system/lang.o"
	import "c/system/exception.o"
	import "c/system/slice.o"
	
	import "c/system/gc.h"
	import "c/system/lang.h"
	import "c/system/string.h"
	import "c/system/exception.h"
	import "c/system/slice.h"
	
	//gui
	import "c/gui/gui.h"
end

extern
	function gccollect:void() = "gccollect"
	function intern_stringlength:int(str:string) = "str_len"
	function intern_stringmid:string(str:string, startPos:int, endPos:int) = "str_mid"
	function intern_stringasc:int(str:string) = "str_asc"
	function intern_stringhash:int(str:string) = "str_hash"
	
	function print:void(text:String) = "print_string"
	function print:void(text:int) = "print_int"
end

class abstract object
	public function toString:string()
		return "object"
	end
	
	public function compare(Other:Object)
		return 0
	end
end

extension int
	function toString()
		return string(this)
	end
end

extension string
	function length()
		return intern_stringlength(this)
	end
	function toString()
		return this
	end
	function hash:int()
		var hash = intern_stringhash(this)
		if hash < 0 
			hash = hash * -1
		end
		
		return hash
	end
	
	function substring(startPos:int, len:int)
		return intern_stringmid(this, startPos, len)
	end
	
	function asc()
		return intern_stringasc(this)
	end
end

class abstract exception
	private var _name = ""
	
	public property name
		get
			return this._name
		end
	end
	
	public function toString()
		return "Exception thrown '" + this.name+"'"
	end
	
	public function new(name)
			this._name = name
	end
end

class final NullPointerException < exception
	public function new()
		this._name = "Null Pointer"
	end
	
	private static var forceCreate = new NullPointerException //den "compile on demand" parser austricken :>, da dieser sonst keine instanz von NullPointerException kompilieren würde.

end

function throwNullPointerException() = "throwNullPointer" force
	throw new NullPointerException
end

class final InvalidSliceOperationException < exception
	public function new()
		this._name = "Invalid Slice Operation"
	end
	
	
	private static var forceCreate = new InvalidSliceOperationException
		
end

function throwSliceException() = "throwSliceException" force
	throw new InvalidSliceOperationException
end


class final OutOfMemoryException < exception
	public function new()
		this._name = "Out Of Memory"
	end
	
	private static var forceCreate = new OutOfMemoryException
	
end

function throwOutOfMemoryException() = "throwOutOfMemory" force
	throw new OutOfMemoryException
end



class<datatype> listValueIterator
	private var l:list<datatype>
	
	public function new(l:list<datatype>)
		this.l = l
	end
	
	public generator:datatype()
		var tmp = this.l.firstElement
		loop tmp != null
			yield return tmp.value
			tmp = tmp.next
		end
		yield break
	end
end

class<datatype> listNodeIterator
	private var l:list<datatype>
	public function new(l:list<datatype>)
		this.l = l
	end
	
	public generator:node<datatype>()
		public generator:datatype()
			var tmp = this.l.firstElement
			loop tmp != null
				yield return tmp
				tmp = tmp.next
			end
			yield break
		end
	end
end

class<datatype> list
	private var _first:node<datatype>
	private var _last:node<datatype>
	private var _size = 0
	
	//Last element value
	public property last
		get
			return this._last.value
		end
	end
	
	//First element value
	public property first
		get
			return this._first.value
		end
	end
	
	//first element
	public property firstElement
		get 
			return this._first
		end
	end
	
	//last element
	public property lastElement
		get 
			return this._last
		end
	end
	//Size
	public property size
		get
			return this._size
		end
	end
	
	//Construct
	public function new()
		this._size = 0
	end
	
	//Adds new element
	public function add(v:datatype)		
		var tmp = new node<datatype>(this._last, v)
		
		if this._last != null
			this._last.setNext(tmp)
		end
		
		if this._first == null
			this._first = tmp
		end
		
		this._last = tmp
		
		this._size = this._size + 1
	end
	
	//Removes an element at "pos"
	public function remove(pos:int)
		var tmp = this._first
		loop var i = 1  to pos
			this._first = this._first.next
		end
		tmp.remove
	end
	
	//creates a new value iterator
	public function values()
		return new listValueIterator<datatype>(this)
	end
	
	//creates a new node iterator
	public function nodes()
		return new listNodeIterator<datatype>(this)
	end
	
	//built in iterator (wraps listIterator)
	public generator:datatype()
		var it = new listValueIterator<datatype>(this)
		loop var i in it
			yield return i
		end
		yield break
	end
end

class<datatype> node
	private var:node<datatype> _prev, _next
	private var _value:datatype
	
	//Which data does it contain?
	public property value
		get
			return this._value
		end
	end
	
	//Previous element
	public property previous
		get
			return this._prev
		end
	end
	
	//Next element
	public property next
		get
			return this._next
		end
	end
	
	//Removes this element
	public function remove()
		var tmp = this._prev
		this._prev._next = this._next
		this._next._prev = tmp
	end
	
	//The next element
	public function setNext(n:node<datatype>)
		this._next = n
	end
	
	//Previous element
	public function setPrev(n:node<datatype>)
		this._prev = n
	end
	
	//constructor
	public function new(prev:node<datatype>, _value:datatype)
		this._prev = prev
		this._value = _value
		this._next = null
	end
end
