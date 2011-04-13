import lumpy


function program()
	var t = new test
	print t.lol["rofl"]
	t.lol["ich bin eine penistorte"] = "elf"
end

class test
	private var holder:string
	public property[position:string] lol:string
		get
			return holder
		end
		set
			holder = value
			print position
			print "set"
		end
	end
	
	public function new() 
	end
end