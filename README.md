# Hospital-Simulator

Ett Work in progress för mig att lära mig mer programmering.

Använder IntelliJ's UI Designer, vilket innebär att det (som jag förstått det) inte går att bygga projektet rakt av utan att själv ha IntelliJ.
Finns dock redan byggd under /out/.
Från projektmappen kan den köras med:
java -cp "out/Hospital Simulator;lib/org.json.jar" hospSim.Main

jdk 19 har använts.

Programmets funktionalitet:

Ett sjukhus med patienter (med en sjukdom), doktorer, och behandlingsrum (med eller utan behandlingsmaskiner) sätts upp.

Man kan sedan: 

- Lägga till patienter, doktorer, och behandlingsrum. 

- Boka konsultationer för patienterna.

- Gå till nästa dag (vilket arkiverar genomförda konsultationer).

Konsultationerna kan endast bokas för tillgängliga patienter, doktorer och behandlingsrum, samt ses det till att doktorn har rätt specialitet för patientens sjukdom och att behandlingsrummet ev. har rätt typ av behandlingsmaskin.
