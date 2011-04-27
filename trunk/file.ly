import lumpy

function program()
	print "lol: "+string(ducktyping(10,20))
	print "rofl: "+string(ducktyping(209.0,30.5))
	print "geht nicht: "+string(ducktyping("Blau","Gelbe scheisse"))
	var t = new test(100)
	t.lol 50.9
	
	schnatter new duck
	schnatter new dog
end

function schnatter(obj)
	obj.laut
end

function ducktyping(test1, test2)
	static try
		return test1 < test2
	end
	catch
		try test1.length() < test2.length()
			return test1.length < test2.length
		end
		catch //ansonsten
			syntaxerror "test1 and test2 must implement '<' operator"
		end
	end
end

class test
	public function new(vari:int)
		print string(vari)
	end
	
	public function lol(v)
		print "lol in Methode: "+string(v)
	end
end

class duck
	public function laut()
		print "schnatter"
	end
	
	public function new()
	end
end

class dog
	public function laut()
		print "wuff"
	end
	
	public function new()
	end
end
