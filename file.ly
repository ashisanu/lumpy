import lumpy


function program()
	var list = new list<int>()
	list.add 10
	list.add 23
	list.add 900

	
	loop var i in list
		print "data: " + string(i)
	end
	
	print "---------- infos: -------------"
	print "size: "+string(list.size)
	print "last: " + string(list.last)
	print "first: "+ string(list.first)
end