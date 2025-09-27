2. Proposed Design Improvements
   2.1 Separate Classes by Responsibility

Issue → stores issue details and state.

Agent → stores expertise, history, queue, and provides methods to add/remove issues.

IssueAssignmentStrategy → interface for different assignment strategies.

IssueManager → manages issues (creation, lookup, validation).

AgentManager → manages agents (creation, expertise lookup).

AssignmentService → handles assignment logic via strategies.

2.2 Use Design Patterns

Strategy Pattern → For different assignment strategies (LeastTotalOpen, MostExperienced, LeastOpenOfType).

Observer Pattern (optional) → Could notify logging when issues are created, assigned, or resolved.

Factory/Builder Pattern → For creating Agent or Issue objects cleanly.

Singleton (optional) → For Solution if system-wide single instance is expected.