## Explain in your own words the following terms: computer, program
A digital computer is a machine that can solve problems for people by carry-
ing out instructions given to it. A sequence of instructions describing how to per-
form a certain task is called a program

## Explain in your own words the term machine language
Together, a computer’s primitive instructions form a language in which peo-
ple can communicate with the computer. Such a language is called a machine
language

## Humans program computers. Yet computers only "talk" machine language. Does that mean humans write programs in machine language? Should they? Discuss.
Humans don't write programs in machine language, because it will be insanly hard
to understand and debug. They usually write programs in higher level languages
and those are translated / interpreted and the result will finally be machine language.

### Translation:
One method of executing a program written in L1 is first to replace each
instruction in it by an equivalent sequence of instructions in L0. The resulting
program consists entirely of L0 instructions. The computer then executes the new
L0 program instead of the old L1 program. This technique is called translation.

### Interpretation
The other technique is to write a program in L0 that takes programs in L1 as
input data and carries them out by examining each instruction in turn and execut-
ing the equivalent sequence of L0 instructions directly. This technique does not
require first generating a new program in L0. It is called interpretation and the
program that carries it out is called an interpreter.

### What are the differences between translation and interpretation?
Translation and interpretation are similar. In both methods, the computer car-
ries out instructions in L1 by executing equivalent sequences of instructions in L0.
The difference is that, in translation, the entire L1 program is first converted to an
L0 program, the L1 program is thrown away, and then the new L0 program is
loaded into the computer’s memory and executed. During execution, the newly
generated L0 program is running and in control of the computer.

### Explain in your own words the term virtual machine.
Rather than thinking in terms of translation or interpretation, it is often sim-
pler to imagine the existence of a hypothetical computer or virtual machine
whose machine language is L1. Let us call this virtual machine M1 (and let us
call the virtual machine corresponding to L0, M0). If such a machine could be
constructed cheaply enough, there would be no need for having language L0 or a
machine that executed programs in L0 at all. People could simply write their pro-
grams in L1 and have the computer execute them directly. Even if the virtual
machine whose language is L1 is too expensive or complicated to construct out of
electronic circuits, people can still write programs for it. These programs can
either be interpreted or translated by a program written in L0 that itself can be
directly executed by the existing computer. In other words, people can write pro-
grams for virtual machines, just as though they really existed.

### Multilevel machines
To make translation or interpretation practical, the languages L0 and L1 must
not be ‘‘too’’ different. This constraint often means that L1, although better than
L0, will still be far from ideal for most applications. This result is perhaps
discouraging in light of the original purpose for creating L1— relieving the pro-
grammer of the burden of having to express algorithms in a language more suited
to machines than people. However, the situation is not hopeless.
The obvious approach is to invent still another set of instructions that is more
people-oriented and less machine-oriented than L1. This third set also forms a
language, which we will call L2 (and with virtual machine M2). People can write
programs in L2 just as though a virtual machine with L2 as its machine language
really existed. Such programs can either be translated to L1 or executed by an
interpreter written in L1.
The invention of a whole series of languages, each one more convenient than
its predecessors, can go on indefinitely until a suitable one is finally achieved.
Each language uses its predecessor as a basis, so we may view a computer using
this technique as a series of layers or levels, one on top of another, as shown in
Fig. 1-1. The bottommost language or level is the simplest and the topmost
language or level is the most sophisticated

### Contemporary multilevel machines
  - microarhitecture
    The next level up is the microarchitecture level. At this level we see a col-
lection of (typically) 8 to 32 registers that form a local memory and a circuit
called an ALU (Arithmetic Logic Unit), which is capable of performing simple
arithmetic operations. The registers are connected to the ALU to form a data
path, over which the data flow. The basic operation of the data path consists of
selecting one or two registers, having the ALU operate on them (for example,
adding them together), and storing the result stored back in some register.
    On some machines the operation of the data path is controlled by a program
called a microprogram. On other machines the data path is controlled directly
by hardware. In the first three editions of this book, we called this level the
‘‘microprogramming level,’’ because in the past it was nearly always a sofware in-
terpreter. Since the data path is now often (partially) controlled directly by
hardware, we changed the name in the previous edition to reflect this
  - isa (instruction set arhitecture)
    At level 2 we have a level that we will call the Instruction Set Architecture
level or (ISA level). Every computer manufacturer publishes a manual for each
of the computers it sells, entitled ‘‘Machine Language Reference Manual’’ or
‘‘Principles of Operation of the Western Wombat Model 100X Computer’’ or
something similar. These manuals are really about the ISA level, not the underly-
ing levels. When they describe the machine’s instruction set, they are in fact de-
scribing the instructions carried out interpretively by the microprogram or hard-
ware execution circuits. If a computer manufacturer provides two interpreters for
one of its machines, interpreting two different ISA levels, it will need to provide
two ‘‘machine language’’ reference manuals, one for each interpreter.
   - operating system
    The next level is usually a hybrid level. Most of the instructions in its
language are also in the ISA level. (There is no reason why an instruction appear-
ing at one level cannot be present at other levels as well.) In addition, there is a
set of new instructions, a different memory organization, the ability to run two or
more programs concurrently, and various other features. More variation exists
between level 3 designs than between those at either level 1 or level 2.
The new facilities added at level 3 are carried out by an interpreter running at
level 2, which, historically, has been called an operating system. Those level 3
instructions that are identical to level 2’s are carried out directly by the micro-SEC. 1.1
LANGUAGES, LEVELS, AND VIRTUAL MACHINES
7
program (or hardwired control), not by the operating system. In other words,
some of the level 3 instructions are interpreted by the operating system and some
are interpreted directly by the microprogram. This is what we mean by ‘‘hybrid’’
level. Throughout this book we will call this level the operating system machine
level.
    - assembly
    Level 4, the assembly language level, is really a symbolic form for one of the
underlying languages. This level provides a method for people to write programs
for levels 1, 2, and 3 in a form that is not as unpleasant as the virtual machine
languages themselves. Programs in assembly language are first translated to level
1, 2, or 3 language and then interpreted by the appropriate virtual or actual
machine. The program that performs the translation is called an assembler
    - problem oriented language level
    Level 5 usually consists of languages designed to be used by applications pro-
grammers with problems to solve. Such languages are often called high-level
languages. Literally hundreds exist. A few of the better known ones are C, C++,
Java, LISP, and Prolog. Programs written in these languages are generally
translated to level 3 or level 4 by translators known as compilers, although occa-
sionally they are interpreted instead. Programs in Java, for example, are usually
first translated to a an ISA-like language called Java byte code, which is then
interpreted.
In some cases, level 5 consists of an interpreter for a specific application
domain, such as symbolic mathematics. It provides data and operations for solv-
ing problems in this domain in terms that people knowledgeable in that domain
can understand easily

### architecture of a level in a multilevel machine, computer arhitecture
The set of data types, operations, and features of each level is called its archi-
tecture. The architecture deals with those aspects that are visible to the user of
that level. Features that the programmer sees, such as how much memory is
available, are part of the architecture. Implementation aspects, such as what kind
of technology is used to implement the memory, are not part of the architecture.
The study of how to design those parts of a computer system that are visible to the
programmers is called computer architecture. In common practice, however,
computer architecture and computer organization mean essentially the same thing.

### hardware
Programs written in a computer’s true machine language
(level 1) can be directly executed by the computer’s electronic circuits (level 0),
without any intervening interpreters or translators. These electronic circuits,
along with the memory and input/output devices, form the computer’s hardware.
Hardware consists of tangible objects—integrated circuits, printed circuit boards,
cables, power supplies, memories, and printers—rather than abstract ideas, algo-
rithms, or instructions.

### software
Software, in contrast, consists of algorithms (detailed instructions telling
how to do something) and their computer representations—namely, programs.
Programs can be stored on hard disk, floppy disk, CD-ROM, or other media, but
the essence of software is the set of instructions that makes up the programs, not
the physical media on which they are recorded.

### In what sense are hardware and software equivalent? Not equivalent?
Hardware and software are logically equivalent.
Any operation performed by software can also be built directly into the
hardware, preferably after it is sufficiently well understood. As Karen Panetta
Lentz put it: ‘‘Hardware is just petrified software.’’ Of course, the reverse is also
true: any instruction executed by the hardware can also be simulated in software.

### Give a short account for:
   - the introduction of microprogramming
The first digital computers, back in the 1940s, had only two levels: the ISA
level, in which all the programming was done, and the digital logic level, which
executed these programs. The digital logic level’s circuits were complicated, dif-
ficult to understand and build, and unreliable.
In 1951, Maurice Wilkes, a researcher at the University of Cambridge, sug-
gested designing a three-level computer in order to drastically simplify the
hardware (Wilkes, 1951). This machine was to have a built-in, unchangeable
interpreter (the microprogram), whose function was to execute ISA-level pro-
grams by interpretation. Because the hardware would now only have to execute
microprograms, which have a limited instruction set, instead of ISA-level pro-
grams, which have a much larger instruction set, fewer electronic circuits would
be needed. Because electronic circuits were then made from vacuum tubes, such
a simplification promised to reduce tube count and hence enhance reliability (i.e.,
the number of crashes per day).
    - the introduction of the operating system
Around 1960 people tried to reduce the amount of wasted time by automating
the operator’s job. A program called an operating system was kept in the com-
puter at all times. The programmer provided certain control cards along with the
program that were read and carried out by the operating system.
    - microcode [migration of functionality to microcode]
Furthermore, once machine designers saw how easy it was to add new instruc-
tions, they began looking around for other features to add to their microprograms.
A few examples of these additions include
    - the elimination of microprogramming
Microprograms grew fat during the golden years of microprogramming
(1960s and 1970s). They also tended to get slower and slower as they acquired
more bulk. Finally, some researchers realized that by eliminating the micropro-
gram, vastly reducing the instruction set, and having the remaining instructions be
directly executed (i.e., hardware control of the data path), machines could be
speeded up. In a certain sense, computer design had come full circle, back to the
way it was before Wilkes invented microprogramming in the first place

### Give a short account of the computer generations
### 1965 sees the launch of PDP-8, one of the most successful minicomputers
(50,000 sold). It introduced an important design innovation. Which is that?
Describe its architecture.

A few years later DEC introduced the PDP-8, which was a 12-bit machine,
but much cheaper than the PDP-1 ($16,000). The PDP-8 had a major innovation:
a single bus, the omnibus, as shown in Fig. 1-6. A bus is a collection of parallel
wires used to connect the components of a computer. This architecture was a
major departure from the memory-centered IAS machine and has been adopted by
nearly all small computers since. DEC eventually sold 50,000 PDP-8s, which
established it as the leader in the minicomputer business.

### Moore's law.
he realized that the number of transistors on a chip was increasing at
a constant rate and predicted this growth would continue for decades to come.
This observation has become known as Moore’s law. Today, Moore’s law is
often expressed as the number of transistors doubling every 18 months.

### Nathan's law
Another factor driving technological improvement is Nathan’s first law of
software (due to Nathan Myhrvold, a former top Microsoft executive). It states:
‘‘Software is a gas. It expands to fill the container holding it.

### Moores circle
Moore’s law has created what economist’s call a virtuous circle. Advances
in technology (transistors/chip) lead to better products and lower prices. Lower
prices lead to new applications (nobody was making video games for computers
when computers cost $10 million each). New applications lead to new markets
and new companies springing up to take advantage of them. The existence of all
these companies leads to competition, which in turn, creates economic demand for
better technologies with which to beat the others. The circle is then roun

### Nathan's law consequences
Modern word processors occupy megabytes
of memory. Future ones will no doubt require gigabytes of memory. (To a first
approximation, the prefixes kilo, mega, and giga mean thousand, million, and bil-
lion, respectively, but see Sec. 1.5 for details.) Software that continues to acquire
features (not unlike boats that continue to acquire barnacles) creates a constant
demand for faster processors, bigger memories, and more I/O capacity.
