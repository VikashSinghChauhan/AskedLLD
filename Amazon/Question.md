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