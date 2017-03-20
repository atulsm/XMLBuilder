# XMLBuilder
A Simple dynamic builder class to create XML's on the fly.
Written in pure java and no additional libraries required.


To get this xml, all you have to do is the following.
```sh
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<Root param1="val1" param2="val2">
    <ListElement>
        <ListParam name="name1" value="val1" />
        <ListParam name="name2" value="val2" />
    </ListElement>
    <Load>
        <SubLoad>
            <Task name="name2" value="val2" />
        </SubLoad>
    </Load>
</Root>
```

Test code:
```sh
Document doc = new XMLBuilder()
				.addElement("Root")
				.addAttribute("param1", "val1")
				.addAttribute("param2", "val2")		
					.addElement("ListElement")		
						.addElement("ListParam")
						.addAttribute("name", "name1")
						.addAttribute("value", "val1")
					.parent()
						.addElement("ListParam")
						.addAttribute("name", "name2")
						.addAttribute("value", "val2")
					.parent()		
				.parent()
					.addElement("Load")
						.addElement("SubLoad")
							.addElement("Task")
							.addAttribute("name", "name2")
							.addAttribute("value", "val2")
				.build();
```
