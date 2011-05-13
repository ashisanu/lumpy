import lumpy




function program()
	var i = new int[10]
	loop var j = 0 to 9
		i[j] = j*j + j
	end
	
	var f = i.toFloatArray()
	print i.length()
	
	loop var j = 0 to f.length() - 1
		print "Array: " + string(f[j])
	end
	var eindim = [10,20,30,40]
	print eindim[2]
	
	var mehrdim = new int[10][10]
	mehrdim = [[10,20,30],[10,20,30],[10,20,30]]
	print mehrdim[0][0]

	loop var arr in i.iterator()
		print "In Array: " + string(arr)
	end
	
	loop var arr in range(40).iterator()
		print "range: " + string(arr)
	end
end
