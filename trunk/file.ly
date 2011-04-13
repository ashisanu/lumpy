import lumpy

class<datatype> list
	public var head:node<datatype>
	
	
	public function new()
	end
	
	public function add(v:datatype)
		this.head = new node<datatype>(this.head, v)
	end
	
	public generator:datatype()
		var tmp = this.head
		loop tmp != null
			yield return tmp.value
			tmp = tmp.previous
		end
		yield break
	end
end

class<datatype> node
	private var:node<datatype> 		prev
	private var:datatype 	_value
	
	public property value
		get
			return this._value
		end
	end
	
	public property Previous
		get
			return this.prev
		end
	end
	
	public function new(prev:node<datatype>, _value:datatype)
		this.prev = prev
		this._value = _value
	end
end

function program()
	var list = new list<int>()
	list.add 10
	list.add 23
	list.add 900

	
	loop var i in list
		print i
	end
end