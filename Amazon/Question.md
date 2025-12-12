I was asked to design the Issue management system(Different priority, Escalations, Wait Queue).

### Aging & priority is main question here apart from thread pool execution. 
Two ways to handle escalation :: 

## frist 
Add time limit with each ticket, if ticket is not processed for t1 time, escalate it to medium priority and 
same from medium to high and etc. 

Worker will keep picking from highest priority to lowest. 
i.e Critical -> high -> medium -> low

Good but some time delay and starvation as on busy day, every ticket will promote to high or critical. 


## Second 

For each priority have some core pool, that will primarily look into that pool only. 
If core pool is full escalate then use reserve pool. 

As implemented in threadpool [corethreadpool, queue, maxthreadpool]



✅ 1. Requirements Gathering (they expect you to clarify)

You must ask questions like:

Functional requirements

Create an Issue / Ticket

Each Issue has:

ID

Title, Description

Priority (LOW, MEDIUM, HIGH, CRITICAL)

Status (OPEN, IN_PROGRESS, ESCALATED, CLOSED)

Assigned agent (optional)

Created time

Agents pick issues based on priority

Escalation rules:

If HIGH isn’t picked in 5 min, escalate

If MEDIUM isn’t picked in 30 min, escalate
(You can propose rules; interviewer just checks your thinking)

Wait Queue:

Unassigned issues wait in a queue

Higher priority → earlier processing

FIFO inside same priority

Non-functional

Concurrency (multiple agents working)

Thread-safe queues

Scalability

✅ 2. Expected LLD / Class Diagram

Amazon expects something like:

Issue
- id
- title
- description
- priority
- status
- createdAt
- assignedAgent

enum Priority { LOW, MEDIUM, HIGH, CRITICAL }
enum Status { OPEN, IN_PROGRESS, ESCALATED, CLOSED }

Queue Manager

Manages wait queues.

IssueQueueManager
- Map<Priority, Queue<Issue>> priorityQueues
+ addIssue(Issue)
+ getNextIssue()
+ removeIssue(Issue)


Use Priority → FIFO concept.

Escalation Manager

Runs periodically and escalates issues that waited too long.

EscalationManager
+ checkAndEscalateIssues()

IssueService

API service layer.

IssueService
+ createIssue()
+ assignIssue()
+ closeIssue()
+ escalateIssue()

Agent
Agent
- id
- name
- assignedIssues

✅ 3. Core Data Structure Expected
👉 Priority + FIFO = Array of queues
Map<Priority, Queue<Issue>> queues;


CRITICAL queue checked first, then HIGH, then MEDIUM, then LOW.

✅ 4. Escalation Logic (important for Amazon)

They expect you to propose something like:

Every 1 minute:
For each issue:
If issue.waitTime > threshold(issue.priority):
escalate(issue)


Escalation does:

Increase priority

Move to higher queue

Update status to ESCALATED

Notify owner

✅ 5. Thread Safety

Use:

ConcurrentLinkedQueue

ReentrantLock for queue updates

Or synchronized if you keep it simple