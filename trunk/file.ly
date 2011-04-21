import lumpy

class test
	public function toString:string()
		return "exception"
	end
	public function new()
	end
end

function program()
	try ex
		try ex
			throwFunc
		end
		catch test
			print ex.tostring()
		end
	end
	catch string
		print "exception: " + ex
	end
	catch int
		print "eine int exceptioN" + string(ex)
	end
	catch test
		print ex.toString()
	end
	catch
		print "wtf"
	end
end

function throwFunc()
	throw new test
	//throw "lol"
	//throw 42
end